package ru.skypro.homework.exception;


import javax.persistence.EntityNotFoundException;

public class AdNotFoundException extends EntityNotFoundException {
    public AdNotFoundException(String message) {
        super(message);
    }
}
