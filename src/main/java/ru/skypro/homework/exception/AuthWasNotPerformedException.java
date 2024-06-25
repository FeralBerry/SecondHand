package ru.skypro.homework.exception;

public class AuthWasNotPerformedException extends RuntimeException {
    public AuthWasNotPerformedException(String message) {
        super(message);
    }
}
