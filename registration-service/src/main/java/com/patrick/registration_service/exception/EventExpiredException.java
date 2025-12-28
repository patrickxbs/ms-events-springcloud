package com.patrick.registration_service.exception;

public class EventExpiredException extends RuntimeException {

    public EventExpiredException(String message) {
        super(message);
    }
}
