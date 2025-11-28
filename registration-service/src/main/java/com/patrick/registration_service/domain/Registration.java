package com.patrick.registration_service.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID eventId;

    private Double currentPrice;
    private Integer quantity;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Registration(UUID eventId, Integer quantity) {
        this.eventId = eventId;
        this.quantity = quantity;
    }
}
