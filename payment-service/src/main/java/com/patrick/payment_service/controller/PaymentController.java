package com.patrick.payment_service.controller;

import com.patrick.payment_service.dto.PaymentDto;
import com.patrick.payment_service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Payment Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Process a payment",
            description = "Create a payment request and returns its payment status.")
    @PostMapping("/{registrationId}")
    public ResponseEntity<PaymentDto> create(@PathVariable UUID registrationId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(registrationId));
    }
}
