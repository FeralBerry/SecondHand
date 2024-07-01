package ru.skypro.homework.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends EntityNotFoundException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}