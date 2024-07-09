package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.skypro.homework.dto.user.RegisterDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserAlreadyExistException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;


@Service
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public AuthServiceImpl(UserDetailsService userDetailsService, UserRepository userRepository, UserMapper userMapper) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    /**
     * Метод проверки пользователя и авторизации.
     * @param userName имя пользователя вводимое пользователем
     * @param password пароль вводимый пользователем
     * @return true при успешной регистрации, IncorrectPasswordException при неверном пароле
     */
    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            String msg = "Incorrect password for user " + userName;
            log.info(msg);
            throw new IncorrectPasswordException(msg);
        }
        log.info("User login " + userName);
        return true;
    }
    /**
     * Регистрация нового пользователя
     * @param registerDTO объект в формате DTO
     * @return true если успешная регистрация и
     * UserAlreadyExistException, если пользователь уже с такими данными существует
     */
    @Override
    public boolean register(RegisterDTO registerDTO) {
        Optional<User> user = userRepository.findByUsername(registerDTO.getUsername());
        if (user.isPresent()) {
            String msg = "User already exist " + registerDTO.getUsername();
            log.info(msg);
            throw new UserAlreadyExistException(msg);
        } else {
            registerDTO.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
            userRepository.save(userMapper.registerDTOToUser(registerDTO));
            log.info("User register " + registerDTO.getUsername());
            return true;
        }
    }

}
