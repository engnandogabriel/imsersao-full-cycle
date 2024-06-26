package com.fullcycle.partner.services.repository;

import com.fullcycle.partner.domain.entities.Spot;

import java.util.Optional;

public interface SpotRepository {
    void save(Spot spot);
    Optional<Spot> getByNameAndEventId(String name, String eventId);
}
