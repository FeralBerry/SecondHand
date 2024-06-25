package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Override
    public User findUser() {
        return null;
    }

    @Override
    public void setPassword(NewPassword newPassword) {

    }

    @Override
    public ru.skypro.homework.dto.user.User getUserData() {
        return null;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updateAvatar(MultipartFile file) {

    }
}
