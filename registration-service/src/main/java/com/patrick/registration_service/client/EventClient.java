package com.patrick.registration_service.client;

import com.patrick.registration_service.dto.event.EventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(value = "EVENT-SERVICE")
public interface EventClient {

    @GetMapping("events/{id}")
    EventDto getEvent(@PathVariable UUID id);

    @PutMapping("events/{eventId}/quantity/{quantity}")
    void decreaseEventCapacity(@PathVariable UUID eventId, @PathVariable Integer quantity);
}
