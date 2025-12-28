package com.patrick.registration_service.exception;

public class InvalidRegistrationPaymentException extends RuntimeException {
    public InvalidRegistrationPaymentException(String message) {
        super(message);
    }
}
