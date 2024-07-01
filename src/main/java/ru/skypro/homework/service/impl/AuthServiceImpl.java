package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.user.RegisterDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserAlreadyExistException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public boolean login(String userName, String password) {
        assert userDetailsService != null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        assert passwordEncoder != null;
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
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
            System.out.println("reg");
            registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            userRepository.save(userMapper.registerDTOToUser(registerDTO));
            log.info("User register " + registerDTO.getUsername());
            return true;
        }
    }

}
