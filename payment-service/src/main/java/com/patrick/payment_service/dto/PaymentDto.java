package com.patrick.payment_service.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "payments")
public record PaymentDto(
        @Id UUID id,
        UUID registrationId,
        PaymentStatus status,
        LocalDateTime moment) {
}
