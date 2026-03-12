package com.example.Streakify.exception;

public class EmailAlreadyExistsException extends DuplicateResourceException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}