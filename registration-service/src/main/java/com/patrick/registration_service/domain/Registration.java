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

    private Long eventId;

    private Double price;
    private Integer quantity;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Registration(Long eventId, Integer quantity) {
        this.eventId = eventId;
        this.quantity = quantity;
    }
}
