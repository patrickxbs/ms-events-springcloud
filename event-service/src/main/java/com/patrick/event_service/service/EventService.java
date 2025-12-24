package com.patrick.event_service.service;

import com.patrick.event_service.domain.Event;
import com.patrick.event_service.dto.EventResponseDto;
import com.patrick.event_service.dto.EventResquestDto;
import com.patrick.event_service.mapper.EventMapper;
import com.patrick.event_service.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventResponseDto create(EventResquestDto eventResquest) {
        Event event = EventMapper.toEvent(eventResquest);
        return EventMapper.toEventDto(eventRepository.save(event));
    }

    public EventResponseDto getById(UUID id) {
        Event event = findEventByIdOrNotFoundException(id);
        return EventMapper.toEventDto(event);
    }

    public List<EventResponseDto> getAll() {
        return eventRepository.findAll().stream().map(EventMapper::toEventDto).toList();
    }

    public void decreaseCapacity(UUID id, Integer quantity) {
        Event event = findEventByIdOrNotFoundException(id);
        event.setCapacity(event.getCapacity() - quantity);
        eventRepository.save(event);
    }

    public List<EventResponseDto> getAllAvailable() {
        return eventRepository.findEventsAvailable(LocalDateTime.now()).stream().map(EventMapper::toEventDto).toList();
    }

    private Event findEventByIdOrNotFoundException(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

}
