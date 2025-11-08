package com.patrick.payment_service.service;

import com.patrick.payment_service.dto.StatusPayment;
import com.patrick.payment_service.dto.PaymentRequest;
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

    public PaymentDto create(PaymentRequest request) {

        boolean approved = new Random().nextBoolean();
        StatusPayment status = approved ? StatusPayment.PAID : StatusPayment.REJECTED;

        PaymentDto payment = new PaymentDto(UUID.randomUUID(), request.registrationId(), status, LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
