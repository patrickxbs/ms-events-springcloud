package com.patrick.event_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponseDto(UUID id, String title, String description, Integer capacity, Boolean remote,
                               LocalDateTime date, String city, String street, String number) {
}
