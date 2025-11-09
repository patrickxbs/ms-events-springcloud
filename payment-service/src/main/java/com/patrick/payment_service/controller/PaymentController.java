package com.patrick.payment_service.controller;

import com.patrick.payment_service.dto.PaymentDto;
import com.patrick.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{registrationId}")
    public ResponseEntity<PaymentDto> create(@PathVariable UUID registrationId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(registrationId));
    }
}
