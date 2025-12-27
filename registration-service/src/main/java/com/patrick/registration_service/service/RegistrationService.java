package com.patrick.registration_service.service;

import com.patrick.registration_service.client.EventClient;
import com.patrick.registration_service.client.PaymentClient;
import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.domain.Status;
import com.patrick.registration_service.dto.event.EventDto;
import com.patrick.registration_service.dto.payment.PaymentDto;
import com.patrick.registration_service.dto.payment.PaymentStatus;
import com.patrick.registration_service.dto.registration.RegistrationRequestDto;
import com.patrick.registration_service.dto.registration.RegistrationResponseDto;
import com.patrick.registration_service.exception.EventExpiredException;
import com.patrick.registration_service.exception.InsufficientEventCapacityException;
import com.patrick.registration_service.exception.InvalidRegistrationPaymentException;
import com.patrick.registration_service.exception.RegistrationNotFoundException;
import com.patrick.registration_service.mapper.RegistrationMapper;
import com.patrick.registration_service.repository.RegistrationRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventClient eventClient;
    private final PaymentClient paymentClient;

    @CircuitBreaker(name = "registrationService")
    @Retry(name = "registrationRetry")
    @RateLimiter(name = "registrationRateLimiter")
    public RegistrationResponseDto create(RegistrationRequestDto request) {
        Registration registration = RegistrationMapper.toRegistration(request);
        EventDto event = eventClient.getEvent(registration.getEventId());

        validateRegistration(event, registration.getQuantity());

        registration.setCurrentPrice(event.ticketPrice());
        Double totalPrice = registration.getCurrentPrice() * registration.getQuantity();
        registration.setTotalPrice(totalPrice);

        registration.setStatus(Status.PENDING_PAYMENT);

        return RegistrationMapper.toDto(registrationRepository.save(registration));
    }

    @CircuitBreaker(name = "paymentService")
    @Retry(name = "paymentRetry")
    public RegistrationResponseDto processPayment(UUID id) {

        Registration registration = registrationRepository.findById(id).orElseThrow(() -> new RegistrationNotFoundException
                (String.format("No registration exists with ID: %s. Please verify the provided registration ID and try again", id)));

        if (registration.getStatus() != Status.PENDING_PAYMENT) {
            throw new InvalidRegistrationPaymentException("Registration is not in a pending state.");
        }

        EventDto event = eventClient.getEvent(registration.getEventId());

        validateRegistration(event, registration.getQuantity());

        PaymentDto payment = paymentClient.createPayment(id);
        log.info("Payment Status: {}", payment.paymentStatus());

        if (payment.paymentStatus().equals(PaymentStatus.PAID)) {
            registration.setStatus(Status.CONFIRMED);
            eventClient.decreaseEventCapacity(registration.getEventId(), registration.getQuantity());
        }

        else registration.setStatus(Status.CANCELLED);

        return RegistrationMapper.toDto(registrationRepository.save(registration));
    }

    public List<RegistrationResponseDto> getAll() {
        return registrationRepository.findAll().stream().map(RegistrationMapper::toDto).toList();
    }

    public List<RegistrationResponseDto> getAllByEvent(UUID eventId) {
        return registrationRepository.findAllByEventId(eventId).stream().map(RegistrationMapper::toDto).toList();
    }

    private void validateRegistration(EventDto event, Integer quantity) {
        if (event.date().isBefore(LocalDateTime.now())) {
            throw new EventExpiredException("Registration impossible: the event has already taken place or the deadline has expired.");
        }
        if (event.capacity() < quantity) {
            throw new InsufficientEventCapacityException("Insufficient capacity. Vacancies available: " + event.capacity());
        }
    }
}

