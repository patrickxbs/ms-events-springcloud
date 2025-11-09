package com.patrick.registration_service.client;

import com.patrick.registration_service.dto.payment.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(value = "PAYMENT-SERVICE")
public interface PaymentClient {

    @PostMapping("/payments/{registrantionId}")
    PaymentDto createPayment(@PathVariable UUID registrantionId);
}
