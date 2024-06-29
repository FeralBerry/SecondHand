package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.user.RegisterDTO;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;


@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public boolean login(String userName, String password) {
        return true;
    }

    @Override
    public boolean register(RegisterDTO registerDTO) {
        return true;
    }

}
