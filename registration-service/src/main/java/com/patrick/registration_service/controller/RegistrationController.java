package com.patrick.registration_service.controller;

import com.patrick.registration_service.dto.registration.RegistrationRequestDto;
import com.patrick.registration_service.dto.registration.RegistrationResponseDto;
import com.patrick.registration_service.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public ResponseEntity<RegistrationResponseDto> create(@RequestBody @Valid RegistrationRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.create(request));
    }

    @Operation(summary = "Process registration payment",
            description = "Triggers the payment flow for a registration and updates its status.")
    @PatchMapping("/{id}/payment")
    public ResponseEntity<RegistrationResponseDto> processPayment(@PathVariable UUID id) {
        return ResponseEntity.ok(registrationService.processPayment(id));
    }

    @Operation(summary = "Get all registrations",
            description = "Returns a complete list of all registrations")
    @GetMapping
    public ResponseEntity<List<RegistrationResponseDto>> getAll() {
        return ResponseEntity.ok(registrationService.getAll());
    }

    @Operation(summary = "Get registrations by event",
            description = "Returns a list of all registrations associated with a specific event ID.")
    @GetMapping("/{eventId}/event")
    public ResponseEntity<List<RegistrationResponseDto>> getAllByEventId(@PathVariable UUID eventId) {
        return ResponseEntity.ok(registrationService.getAllByEvent(eventId));
    }
}
