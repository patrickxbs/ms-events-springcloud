package com.patrick.event_service.repository;

import com.patrick.event_service.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("""
    SELECT e FROM Event e
    WHERE e.date >= :date
      AND e.capacity > 0
    """)
    List<Event> findEventsAvailable(@Param("date") LocalDateTime date);
}
