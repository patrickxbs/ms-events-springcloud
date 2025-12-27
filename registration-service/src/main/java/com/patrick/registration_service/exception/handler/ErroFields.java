package com.patrick.registration_service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErroFields {

    private LocalDateTime timestamp;
    private String title;
    private String message;
}
