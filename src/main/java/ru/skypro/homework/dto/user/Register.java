package ru.skypro.homework.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @Size(min = 4, max = 32)
    private String username;

    @Size(min = 8, max = 16)
    private String password;

    @Size(min = 2, max = 16)
    private String firstName;

    @Size(min = 2, max = 16)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    private Role role;
}
