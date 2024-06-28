package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private static final long MAX_SIZE = 3 * 1024 * 1024;

    @Override
    @Transactional
    public Image saveImage(MultipartFile imageFile) {
        return null;
    }
    @Override
    public void deleteImage(Long imageId) {

    }

    @Override
    public byte[] getImage(Long imageId) {
        return null;
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
