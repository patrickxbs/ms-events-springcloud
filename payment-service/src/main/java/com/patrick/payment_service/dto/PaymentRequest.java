package com.patrick.payment_service.dto;

import java.util.UUID;

public record PaymentRequest(UUID registrationId) {
}
