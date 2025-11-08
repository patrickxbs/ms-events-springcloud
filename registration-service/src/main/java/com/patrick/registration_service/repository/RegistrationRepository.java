package com.patrick.registration_service.repository;

import com.patrick.registration_service.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
}
