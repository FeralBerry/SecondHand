package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        userService.setPassword(newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserData() {
        UserDTO userDTO = userService.getUserData();
        return ResponseEntity.ok(userDTO);
    }

     @PatchMapping("/me")
    public ResponseEntity<?> updateUserData(@RequestBody UpdateUserDTO updateUserDTO) {
        UpdateUserDTO user = userService.updateUser(updateUserDTO);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updateUserAvatar(@RequestPart MultipartFile image) {
        userService.updateAvatar(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
