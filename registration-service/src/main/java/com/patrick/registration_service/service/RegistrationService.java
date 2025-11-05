package com.patrick.registration_service.service;

import com.patrick.registration_service.domain.Registration;
import com.patrick.registration_service.domain.Status;
import com.patrick.registration_service.dto.RegistrationRequest;
import com.patrick.registration_service.dto.RegistrationResponse;
import com.patrick.registration_service.mapper.RegistrationMapper;
import com.patrick.registration_service.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationResponse create(RegistrationRequest request) {
        Registration registration = RegistrationMapper.toRegistration(request);

        registration.setPrice(1D);
        Double totalPrice = registration.getPrice() * registration.getQuantity();
        registration.setTotalPrice(totalPrice);

        registration.setStatus(Status.PENDING_PAYMENT);

        return RegistrationMapper.toDto(registrationRepository.save(registration));
    }
}
