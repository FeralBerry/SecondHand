package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.exception.UserAlreadyExistException;
import ru.skypro.homework.exception.UserNotFoundException;

/**
 * Сервис для операций, связанных с пользователем.
 * Этот сервис обрабатывает пользователя, управление паролями и извлечение и обновление данных пользователя.
 */
public interface UserService {

    /**
     * Находит пользователя.
     * @return User пользователь.
     * @throws UserNotFoundException если пользователь не найден.
     * @throws UserAlreadyExistException если пользователь c такими данными уже зарегистрирован.
     */
    User findUser();

    /**
     * Устанавливает новый пароль для текущего пользователя.
     * @param newPassword Новый пароль для установки.
     * @throws IncorrectPasswordException если текущий пароль не совпадает или пароль не прошел валидацию.
     */
    void setPassword(NewPassword newPassword);
    /**
     * Извлекает данные текущего пользователя в виде объекта передачи данных (DTO).
     * @return User DTO, содержащий данные пользователя.
     */
    ru.skypro.homework.dto.user.User getUserData();

    /**
     * Обновляет данные текущего пользователя на основе предоставленного DTO.
     * @param updateUser DTO с обновленными данными пользователя.
     * @return UpdateUser Обновленный DTO.
     */
    UpdateUser updateUser(UpdateUser updateUser);

    /**
     * Обновляет аватар для текущего пользователя.
     * @param file Новое изображение для установки в качестве аватара.
     */
    void updateAvatar(MultipartFile file);
}
