package com.example.domain.exceptions;

public class DataUnavailableException extends Exception {

    public DataUnavailableException() {
        super("Ошибка доступа к данным!");
    }

}
