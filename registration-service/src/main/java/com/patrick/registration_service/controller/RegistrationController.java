package com.patrick.registration_service.controller;

import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.dto.registration.RegistrationRequest;
import com.patrick.registration_service.dto.registration.RegistrationResponse;
import com.patrick.registration_service.service.RegistrationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Registration Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Operation(summary = "Create a registration",
            description = "Registers a user for a specific event and calculates the total price.")
    @PostMapping
    public ResponseEntity<RegistrationResponse> create(@RequestBody RegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.create(request));
    }

    @Operation(summary = "Process registration payment",
            description = "Triggers the payment flow for a registration and updates its status.")
    @PatchMapping("/{id}")
    public ResponseEntity<RegistrationResponse> processPayment(@PathVariable UUID id) {
        registrationService.simulatePayment(id);
        return ResponseEntity.noContent().build();
    }
}
