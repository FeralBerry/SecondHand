package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User findUser() {
        return null;
    }


    @Override
    public void setPassword(NewPassword newPassword) {

    }

    @Override
    public UserDTO getUserData() {
        return null;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        return null;
    }
    @Override
    public void updateAvatar(MultipartFile image) {

    }
}
