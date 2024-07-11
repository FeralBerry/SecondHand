package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public UserServiceImpl(UserRepository userRepository, ImageService imageService, UserMapper userMapper){
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.userMapper = userMapper;
    }
    @Override
    public User findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found " + username));
    }
    @Override
    public void setPassword(NewPassword newPassword) {
        User user = findUser();
        if (!bCryptPasswordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            log.warn("Incorrect old password, user " + user.getId());
            throw new IncorrectPasswordException("Incorrect old password, user " + user.getId());
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        log.info("Password updated for user " + user.getId());
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
        User user = findUser();
        userMapper.updateUserDTOToUser(updateUserDTO, user);
        userRepository.save(user);

        return updateUserDTO;
    }
    @Override
    public void updateAvatar(MultipartFile image) {
        User user = findUser();
        if (user.getAvatar() != null) {
            imageService.deleteImage(user.getAvatar().getId());
        }
        Image newImage = imageService.saveImage(image);
        user.setAvatar(newImage);
        userRepository.save(user);
    }
}
