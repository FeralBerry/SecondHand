package ru.skypro.homework.service;

import ru.skypro.homework.dto.user.RegisterDTO;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDTO register);
}
