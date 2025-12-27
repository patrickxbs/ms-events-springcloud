package com.patrick.event_service.exception.handler;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionDetails extends ErroFields {

    public ExceptionDetails(LocalDateTime timestamp, String title, String message) {
        super(timestamp, title, message);
    }
}
