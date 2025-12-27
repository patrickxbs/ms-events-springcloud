package com.patrick.registration_service.exception;

public class EventExpiredException extends ApiException {

    public EventExpiredException(String message) {
        super(message);
    }
}
