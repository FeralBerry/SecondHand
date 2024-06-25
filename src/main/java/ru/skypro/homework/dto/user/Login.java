package ru.skypro.homework.dto.user;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @Size(min = 4, max = 32)
    private String username;

    @Size(min = 8, max = 16)
    private String password;
}
