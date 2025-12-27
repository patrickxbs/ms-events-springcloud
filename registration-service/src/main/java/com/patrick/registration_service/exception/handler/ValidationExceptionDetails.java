package com.patrick.registration_service.exception.handler;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ValidationExceptionDetails extends ErroFields {

    private final String fields;
    private final String fieldsMessage;

    public ValidationExceptionDetails(LocalDateTime timestamp, String title, String message, String fields, String fieldsMessage) {
        super(timestamp, title, message);
        this.fields = fields;
        this.fieldsMessage = fieldsMessage;
    }
}
