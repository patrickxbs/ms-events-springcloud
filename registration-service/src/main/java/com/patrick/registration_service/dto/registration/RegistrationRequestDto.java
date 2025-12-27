package com.patrick.registration_service.dto.registration;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegistrationRequestDto(@NotNull UUID eventId,
                                     @NotNull @Min(value = 1) Integer quantity) {
}
