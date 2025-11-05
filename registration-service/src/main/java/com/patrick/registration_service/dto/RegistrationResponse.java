package com.patrick.registration_service.dto;

import com.patrick.registration_service.domain.Status;

public record RegistrationResponse(Long id, Long eventId, Double price, Integer quantity, Double totalPrice, Status status) {
}
