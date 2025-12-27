package com.patrick.registration_service.mapper;

import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.dto.registration.RegistrationRequestDto;
import com.patrick.registration_service.dto.registration.RegistrationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public static Registration toRegistration(RegistrationRequestDto dto) {
        return new Registration(dto.eventId(), dto.quantity());
    }

    public static RegistrationResponseDto toDto(Registration registration) {
        return new RegistrationResponseDto(registration.getId(), registration.getEventId(),
                registration.getQuantity(), registration.getTotalPrice(), registration.getStatus());
    }
}
