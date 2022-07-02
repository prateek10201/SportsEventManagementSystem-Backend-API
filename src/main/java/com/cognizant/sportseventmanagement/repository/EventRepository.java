package com.cognizant.sportseventmanagement.repository;

import com.cognizant.sportseventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findEventByEventId(Long eventId);
    Optional<Event> findEventByEventName(String eventName);
}
