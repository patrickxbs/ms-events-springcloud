package com.patrick.event_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record EventRequestDto(@NotBlank String title, @NotBlank String description, @NotNull Integer capacity,
                              @NotNull @Positive Double ticketPrice, @NotNull Boolean remote, @NotNull LocalDateTime date,
                              @NotBlank String city, @NotBlank String street, @NotBlank String number) {
}
