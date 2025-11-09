package com.patrick.event_service.controller;

import com.patrick.event_service.dto.EventResponseDto;
import com.patrick.event_service.dto.EventResquestDto;
import com.patrick.event_service.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> create(@RequestBody EventResquestDto eventDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(eventDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @PutMapping("/{id}/quantity/{quantity}")
    public ResponseEntity<Void> decreaseCapacity(@PathVariable UUID id, @PathVariable Integer quantity) {
        eventService.decreaseCapacity(id, quantity);
        return ResponseEntity.noContent().build();
    }
}
