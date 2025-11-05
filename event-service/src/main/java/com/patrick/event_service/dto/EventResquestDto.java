package com.patrick.event_service.dto;

import java.time.LocalDateTime;

public record EventResquestDto(String title, String description, Integer capacity, Boolean remote,
                               LocalDateTime date, String city, String street, String number) {
}
