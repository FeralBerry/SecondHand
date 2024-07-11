package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class ImageController {
    @GetMapping(value = "/image/{path}")
    public @ResponseBody byte[] getImage(@PathVariable("path") String path) throws IOException {
        File fi = new File("image/" + path);
        return Files.readAllBytes(fi.toPath());
    }
}
