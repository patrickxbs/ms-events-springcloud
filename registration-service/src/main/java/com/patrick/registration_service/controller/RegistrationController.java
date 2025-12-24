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

import java.util.List;
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
    @PatchMapping("/{id}/payment")
    public ResponseEntity<RegistrationResponse> processPayment(@PathVariable UUID id) {
        return ResponseEntity.ok(registrationService.processPayment(id));
    }

    @Operation(summary = "Get all registrations",
            description = "Returns a complete list of all registrations")
    @GetMapping
    public ResponseEntity<List<RegistrationResponse>> getAll() {
        return ResponseEntity.ok(registrationService.findAll());
    }

    @Operation(summary = "Get registrations by event",
            description = "Returns a list of all registrations associated with a specific event ID.")
    @GetMapping("/{id}/event")
    public ResponseEntity<List<RegistrationResponse>> getAllByEventId(@PathVariable UUID eventId) {
        return ResponseEntity.ok(registrationService.findAllByEvent(eventId));
    }
}
