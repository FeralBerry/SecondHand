package ru.skypro.homework.dto.user;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Класс для представления нового пароля пользователя.
 * Класс используется для передачи данных о смене пароля пользователя,
 * включая текущий пароль (currentPassword) и новый пароль (newPassword)
 * с ограничением на длину пароля от 8 до 16 символов.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {
    @Size(min = 8, max = 16)
    private String currentPassword;
    @Size(min = 8, max = 16)
    private String newPassword;
}