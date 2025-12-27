package com.patrick.registration_service.exception;

public class RegistrationNotFoundException extends ApiException {

    public RegistrationNotFoundException(String message) {
        super(message);
    }
}
