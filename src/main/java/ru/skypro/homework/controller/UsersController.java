package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.service.UserService;

@Slf4j
@Tag(name = "Контроллер администратора работа с отчетами", description = "Контроллер администратора для действий с отчетами")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Управление данными пользователей")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED: пользователь не авторизован"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN: нет доступа"),
        @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR: ошибка сервера при обработке запроса")})
public class UsersController {
    private final UserService userService;
    @Operation(summary = "Обновление пароля")
    @ApiResponse(
            responseCode = "200", description = "OK: пароль изменен")
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        userService.setPassword(newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
