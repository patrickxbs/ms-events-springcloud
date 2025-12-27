package com.patrick.registration_service.exception;

public class InvalidRegistrationPaymentException extends ApiException {
    public InvalidRegistrationPaymentException(String message) {
        super(message);
    }
}
