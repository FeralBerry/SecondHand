package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;
public interface UserService {

    User findUser();
    void setPassword(NewPassword newPassword);
    UserDTO getUserData();
    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);
    void updateAvatar(MultipartFile file);
}
