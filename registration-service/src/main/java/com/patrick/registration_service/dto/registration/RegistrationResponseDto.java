package com.patrick.registration_service.dto.registration;

import com.patrick.registration_service.domain.Status;

import java.util.UUID;

public record RegistrationResponseDto(UUID id, UUID eventId, Integer quantity, Double totalPrice, Status status) {
}
