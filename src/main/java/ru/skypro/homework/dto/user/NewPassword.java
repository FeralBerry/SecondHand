package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPassword {

    @Size(min = 8, max = 16)
    private String currentPassword;

    @Size(min = 8, max = 16)
    private String newPassword;
}