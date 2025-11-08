package com.patrick.registration_service.dto;

import com.patrick.registration_service.domain.Status;

import java.util.UUID;

public record RegistrationResponse(UUID id, Long eventId, Double price, Integer quantity, Double totalPrice, Status status) {
}
