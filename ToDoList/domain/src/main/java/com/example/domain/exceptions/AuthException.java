package com.example.domain.exceptions;

public class AuthException extends Exception {

    public AuthException() {
        super("Ошибка аутентификации!");
    }
}
