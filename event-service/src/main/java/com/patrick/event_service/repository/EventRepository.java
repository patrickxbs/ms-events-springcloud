package com.patrick.event_service.repository;

import com.patrick.event_service.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
