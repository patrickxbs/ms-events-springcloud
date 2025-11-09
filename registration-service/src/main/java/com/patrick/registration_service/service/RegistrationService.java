package com.patrick.registration_service.service;

import com.patrick.registration_service.client.EventClient;
import com.patrick.registration_service.client.PaymentClient;
import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.domain.Status;
import com.patrick.registration_service.dto.event.EventDto;
import com.patrick.registration_service.dto.payment.PaymentDto;
import com.patrick.registration_service.dto.payment.PaymentStatus;
import com.patrick.registration_service.dto.registration.RegistrationRequest;
import com.patrick.registration_service.dto.registration.RegistrationResponse;
import com.patrick.registration_service.mapper.RegistrationMapper;
import com.patrick.registration_service.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventClient eventClient;
    private final PaymentClient paymentClient;

    public RegistrationResponse create(RegistrationRequest request) {
        Registration registration = RegistrationMapper.toRegistration(request);
        EventDto event = eventClient.getEvent(registration.getEventId());

        verifyCapacity(event.capacity(), registration.getQuantity());

        Double totalPrice = event.ticketPrice() * registration.getQuantity();
        registration.setTotalPrice(totalPrice);

        registration.setStatus(Status.PENDING_PAYMENT);

        return RegistrationMapper.toDto(registrationRepository.save(registration));
    }

    public void simulatePayment(UUID id) {

        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Registration not found"));

        EventDto event = eventClient.getEvent(registration.getEventId());

        verifyCapacity(event.capacity(), registration.getQuantity());

        PaymentDto payment = paymentClient.createPayment(id);
        log.info(String.valueOf(payment.paymentStatus()));
        if (payment.paymentStatus().equals(PaymentStatus.PAID)) {
                registration.setStatus(Status.CONFIRMED);
            eventClient.decreaseEventCapacity(registration.getEventId(), registration.getQuantity());
        }

        else registration.setStatus(Status.CANCELLED);

        registrationRepository.save(registration);
    }

    private void verifyCapacity(Integer capacity, Integer quantity) {
        if (capacity < quantity)
            throw new RuntimeException("Not enough seats available. Available capacity: " + capacity);
    }
}
