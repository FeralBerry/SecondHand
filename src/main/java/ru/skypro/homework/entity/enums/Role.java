package ru.skypro.homework.entity.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum класс, который содержит роли пользователей.
 */
public enum Role implements GrantedAuthority {

    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
