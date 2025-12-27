package com.patrick.registration_service.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
