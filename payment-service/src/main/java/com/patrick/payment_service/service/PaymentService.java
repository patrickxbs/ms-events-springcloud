package com.patrick.payment_service.service;

import com.patrick.payment_service.dto.PaymentStatus;
import com.patrick.payment_service.dto.PaymentDto;
import com.patrick.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentDto create(UUID registrationId) {

        boolean approved = new Random().nextBoolean();
        PaymentStatus status = approved ? PaymentStatus.PAID : PaymentStatus.REJECTED;

        PaymentDto payment = new PaymentDto(UUID.randomUUID(), registrationId, status, LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
