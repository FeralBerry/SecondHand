package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public User findUser() {
        return null;
    }
    @Override
    public void setPassword(NewPassword newPassword) {
        User user = findUser();

        if (!bCryptPasswordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            log.warn("Incorrect old password, user {}", user.getId());
            throw new IncorrectPasswordException(String.format("Incorrect old password, user %d", user.getId()));
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        log.info("Password updated for user {}", user.getId());
    }
    /**
     * Получение данных пользователя
     * @return UserDTO
     */
    @Override
    public UserDTO getUserData() {
        User user = findUser();
        return userMapper.toUserDTO(user);
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
        return null;
    }
    @Override
    public void updateAvatar(MultipartFile image) {

    }
}
