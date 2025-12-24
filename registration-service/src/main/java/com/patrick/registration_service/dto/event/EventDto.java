package com.patrick.registration_service.dto.event;

import java.time.LocalDateTime;

public record EventDto(String title, Integer capacity, Double ticketPrice, LocalDateTime date) {
}
