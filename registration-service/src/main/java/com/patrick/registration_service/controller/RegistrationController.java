package com.patrick.registration_service.controller;

import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.dto.registration.RegistrationRequest;
import com.patrick.registration_service.dto.registration.RegistrationResponse;
import com.patrick.registration_service.service.RegistrationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> create(@RequestBody RegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.create(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RegistrationResponse> create(@PathVariable UUID id) {
        registrationService.simulatePayment(id);
        return ResponseEntity.noContent().build();
    }
}
