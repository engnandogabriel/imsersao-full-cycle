package com.fullcycle.partner.services.repository;

import com.fullcycle.partner.domain.entities.Event;

import java.util.Optional;

public interface EventRepository {
    void save(Event event);
    Optional<Event> getById(String id) throws Exception;
    void update(Event event);
    void delete(String id);
}
