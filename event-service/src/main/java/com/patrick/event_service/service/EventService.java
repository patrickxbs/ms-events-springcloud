package com.patrick.event_service.service;

import com.patrick.event_service.domain.Event;
import com.patrick.event_service.dto.EventResponseDto;
import com.patrick.event_service.dto.EventResquestDto;
import com.patrick.event_service.mapper.EventMapper;
import com.patrick.event_service.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        return EventMapper.toEventDto(event);
    }

}
