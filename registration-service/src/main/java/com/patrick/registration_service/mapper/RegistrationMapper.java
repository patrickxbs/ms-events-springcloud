package com.patrick.registration_service.mapper;

import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.dto.RegistrationRequest;
import com.patrick.registration_service.dto.RegistrationResponse;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public static Registration toRegistration(RegistrationRequest dto) {
        return new Registration(dto.eventId(), dto.quantity());
    }

    public static RegistrationResponse toDto(Registration registration) {
        return new RegistrationResponse(registration.getId(), registration.getEventId(), registration.getPrice(),
                registration.getQuantity(), registration.getTotalPrice(), registration.getStatus());
    }
}
