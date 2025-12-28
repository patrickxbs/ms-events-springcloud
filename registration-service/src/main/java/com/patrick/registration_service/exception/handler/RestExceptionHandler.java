package com.patrick.registration_service.exception.handler;

import com.patrick.registration_service.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RegistrationNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerRegistrationNotFoundException(RegistrationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDetails(
                LocalDateTime.now(),
                "REGISTRATION_NOT_FOUND",
                ex.getMessage()));
    }

    @ExceptionHandler(EventExpiredException.class)
    public ResponseEntity<ExceptionDetails> handlerEventExpiredException(EventExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY ).body(new ExceptionDetails(
                LocalDateTime.now(),
                "EVENT_EXPIRED",
                ex.getMessage()));
    }

    @ExceptionHandler({
            InsufficientEventCapacityException.class,
            InvalidRegistrationPaymentException.class
    })
    public ResponseEntity<ExceptionDetails> handlerInsufficientEventCapacityException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDetails(
                LocalDateTime.now(),
                "CONFLICT",
                ex.getMessage()));
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ExceptionDetails> handleServiceUnavailable(ServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ExceptionDetails(
                        LocalDateTime.now(),
                        "SERVICE_UNAVAILABLE",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleGeneric(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDetails(
                        LocalDateTime.now(),
                        "INTERNAL_SERVER_ERROR",
                        "An unexpected error occurred."
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        String messageField = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationExceptionDetails(
                LocalDateTime.now(),
                "INVALID_FIELDS",
                "One or more fields are invalid.",
                fields,
                messageField));
    }
}
