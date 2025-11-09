package com.patrick.registration_service.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentDto(@JsonProperty("status") PaymentStatus paymentStatus) {
}
