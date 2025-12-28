package com.patrick.registration_service.config;

import com.patrick.registration_service.exception.EventExpiredException;
import com.patrick.registration_service.exception.InsufficientEventCapacityException;
import com.patrick.registration_service.exception.InvalidRegistrationPaymentException;
import com.patrick.registration_service.exception.RegistrationNotFoundException;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerConfigCustomizer paymentServiceCustomizer() {
        return CircuitBreakerConfigCustomizer.of(
                "paymentService",
                builder -> builder.recordException(ex ->
                        !(ex instanceof InvalidRegistrationPaymentException
                                || ex instanceof RegistrationNotFoundException
                                || ex instanceof EventExpiredException
                                || ex instanceof InsufficientEventCapacityException)));
    }

    @Bean
    public CircuitBreakerConfigCustomizer registrationServiceCustomizer() {
        return CircuitBreakerConfigCustomizer.of(
                "registrationService",
                builder -> builder.recordException(ex ->
                        !(ex instanceof  EventExpiredException
                                || ex instanceof InsufficientEventCapacityException)));
    }
}
