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

    @CircuitBreaker(name = "registrationService", fallbackMethod = "fallbackCreate")
    @Retry(name = "registrationRetry")
    @RateLimiter(name = "registrationRateLimiter")
    public RegistrationResponse create(RegistrationRequest request) {
        Registration registration = RegistrationMapper.toRegistration(request);
        EventDto event = eventClient.getEvent(registration.getEventId());

        validateRegistration(event, registration.getQuantity());

        registration.setCurrentPrice(event.ticketPrice());
        Double totalPrice = registration.getCurrentPrice() * registration.getQuantity();
        registration.setTotalPrice(totalPrice);

        registration.setStatus(Status.PENDING_PAYMENT);

        return RegistrationMapper.toDto(registrationRepository.save(registration));
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")
    @Retry(name = "paymentRetry")
    public RegistrationResponse processPayment(UUID id) {

        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Registration not found"));

        if (registration.getStatus() != Status.PENDING_PAYMENT) {
            throw new RuntimeException("Registration is not in a pending state.");
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

    public List<RegistrationResponse> getAll() {
        return registrationRepository.findAll().stream().map(RegistrationMapper::toDto).toList();
    }

    public List<RegistrationResponse> getAllByEvent(UUID eventId) {
        return registrationRepository.findAllByEventId(eventId).stream().map(RegistrationMapper::toDto).toList();
    }

    private void validateRegistration(EventDto event, Integer quantity) {
        if (event.date().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Registration impossible: the event has already taken place or the deadline has expired.");
        }
        if (event.capacity() < quantity) {
            throw new RuntimeException("Insufficient capacity. Vacancies available: " + event.capacity());
        }
    }

    public RegistrationResponse fallbackCreate(RegistrationRequest request, Exception ex) {
        log.error("Fallback triggered for creation. Reason: {}", ex.getMessage());
        return new RegistrationResponse(null, request.eventId(), request.quantity(), 0D, Status.FAILED);
    }

    public RegistrationResponse fallbackPayment(UUID id, Exception ex) {
        log.error("Fallback triggered for payment of ID {}. Reason: {}", id, ex.getMessage());
        return new RegistrationResponse(id, null, null, 0D, Status.PENDING_PAYMENT);
    }
}

