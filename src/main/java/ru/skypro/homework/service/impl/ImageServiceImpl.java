package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.ImageSaveException;
import ru.skypro.homework.exception.InvalidFileException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    final String LOG_INVALID_SIZE_MSG = "Invalid file size. Maximum allowed size is 3MB.";
    final String LOG_INVALID_TYPE_MSG = "Invalid file type. Only JPEG and PNG are allowed.";
    final String LOG_INVALID_NAME_MSG = "Invalid file name. It contains illegal characters.";
    final String LOG_CREATED_DIRECTORY_MSG = "Created directory for file uploads: {}";
    final String LOG_ERROR_CREATING_DIRECTORY_MSG = "Could not create the directory for file uploads.";
    final String LOG_IMAGE_SAVED_MSG = "Image saved with path [{}]";
    final String LOG_ERROR_SAVING_IMAGE_MSG = "Error saving image: {}";

    private static final long MAX_SIZE = 3 * 1024 * 1024;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public Image saveImage(MultipartFile imageFile) {
        if (!isValidSize(imageFile)) {
            log.warn(LOG_INVALID_SIZE_MSG);
            throw new InvalidFileException("File size is invalid. Maximum allowed size is 3MB.");
        }

        if (!isValidType(imageFile)) {
            log.warn(LOG_INVALID_TYPE_MSG);
            throw new InvalidFileException("File type is invalid. Only JPEG and PNG are allowed.");
        }

        String originalFilename = Objects.requireNonNull(imageFile.getOriginalFilename());
        if (!isValidName(originalFilename)) {
            log.warn(LOG_INVALID_NAME_MSG);
            throw new InvalidFileException("File name is invalid. It contains illegal characters.");
        }

        try {
            String directoryPath = System.getProperty("user.dir") + "/image/";
            String filename = generateUniqueFilename(originalFilename);
            String filePath = directoryPath + filename;

            File directory = new File(directoryPath);
            if (!directory.exists()) {
                boolean isCreated = directory.mkdirs();
                if (!isCreated) {
                    log.error(LOG_ERROR_CREATING_DIRECTORY_MSG);
                    throw new IOException(LOG_ERROR_CREATING_DIRECTORY_MSG);
                }
                log.info(LOG_CREATED_DIRECTORY_MSG, directoryPath);
            }

            File file = new File(filePath);
            imageFile.transferTo(file);

            Image image = new Image();
            image.setImagePath(filename);
            image = imageRepository.save(image);

            log.info(LOG_IMAGE_SAVED_MSG, filePath);
            return image;
        } catch (IOException e) {
            log.error(LOG_ERROR_SAVING_IMAGE_MSG, e.getMessage());
            throw new ImageSaveException("Failed to save image", e);
        }
    }
    @Override
    public void deleteImage(Long imageId) {
        if (imageRepository.existsById(imageId)) {
            imageRepository.deleteById(imageId);
        }
    }
    @Override
    public byte[] getImage(Long imageId) {
        Optional<Image> imageOpt = imageRepository.findById(imageId);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();
            String filePath = image.getImagePath();
            Path path = Paths.get(filePath);
            try {
                return Files.readAllBytes(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new ImageNotFoundException("Not found images {}");
        }
    }

    public String generateUniqueFilename(String originalFilename) {
        log.debug("Generated unique filename: {}", originalFilename);
        return UUID.randomUUID() + "_" + originalFilename;
    }

    public boolean isValidSize(MultipartFile file) {
        boolean isValid = file.getSize() <= MAX_SIZE;
        if (!isValid) {
            log.warn("File size {} is invalid. Maximum allowed size is {} bytes.", file.getSize(), MAX_SIZE);
        } else {
            log.debug("File size {} is within the valid range.", file.getSize());
        }
        return isValid;
    }
    public boolean isValidType(MultipartFile file) {
        String contentType = file.getContentType();
        assert contentType != null;
        boolean isValid = contentType.equals("image/jpeg") || contentType.equals("image/png");
        if (!isValid) {
            log.warn("File type '{}' is invalid. Only JPEG and PNG are allowed.", contentType);
        } else {
            log.debug("File type '{}' is valid.", contentType);
        }
        return isValid;
    }
    public boolean isValidName(String filename) {
        boolean isValid = !filename.contains("..") && !filename.matches(".*[<>\"].*");
        if (!isValid) {
            log.warn("Filename '{}' is invalid. It contains illegal characters.", filename);
        } else {
            log.debug("Filename '{}' is valid.", filename);
        }
        return isValid;
    }

}
