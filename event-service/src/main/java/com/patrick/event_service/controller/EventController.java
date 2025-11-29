package com.patrick.event_service.controller;

import com.patrick.event_service.dto.EventResponseDto;
import com.patrick.event_service.dto.EventResquestDto;
import com.patrick.event_service.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Event Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Create a new event",
            description = "Adds a new event with name, capacity, date and ticket price.")
    @PostMapping
    public ResponseEntity<EventResponseDto> create(@RequestBody EventResquestDto eventDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(eventDto));
    }

    @Operation(summary = "Decrease event capacity",
            description = "Reduces the event capacity after a successful registration.")
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @Operation(summary = "List all events",
            description = "Returns a list of all registered events.")
    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @Operation(summary = "Decrease event capacity",
            description = "Reduces the event capacity after a successful registration.")
    @PutMapping("/{id}/quantity/{quantity}")
    public ResponseEntity<Void> decreaseCapacity(@PathVariable UUID id, @PathVariable Integer quantity) {
        eventService.decreaseCapacity(id, quantity);
        return ResponseEntity.noContent().build();
    }
}
