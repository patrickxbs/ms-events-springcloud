package com.patrick.registration_service.dto.registration;

import java.util.UUID;

public record RegistrationRequest(UUID eventId, Integer quantity) {
}
