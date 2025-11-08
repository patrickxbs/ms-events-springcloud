package com.patrick.payment_service.repository;

import com.patrick.payment_service.dto.PaymentDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentDto, Long> {
}
