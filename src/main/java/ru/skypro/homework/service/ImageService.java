package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

public interface ImageService {

    Image saveImage(MultipartFile imageFile);
    void deleteImage(Long imageId);
    byte[] getImage(Long imagerId);
}
