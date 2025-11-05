package com.patrick.event_service.mapper;

import com.patrick.event_service.domain.Address;
import com.patrick.event_service.domain.Event;
import com.patrick.event_service.dto.EventResponseDto;
import com.patrick.event_service.dto.EventResquestDto;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public static Event toEvent(EventResquestDto dto) {
        Address address = new Address(dto.city(), dto.street(), dto.number());
        return new Event(null, dto.title(), dto.description(), dto.capacity(), dto.remote(), dto.date(), address);
    }

    public static EventResponseDto toEventDto(Event event) {
        return new EventResponseDto(event.getId(), event.getTitle(), event.getDescription(), event.getCapacity(), event.getRemote(),
                event.getDate(), event.getAddress().getCity(),event.getAddress().getStreet(), event.getAddress().getNumber());
    }
}
