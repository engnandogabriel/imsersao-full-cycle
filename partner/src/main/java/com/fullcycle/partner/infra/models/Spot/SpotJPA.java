package com.fullcycle.partner.infra.models.Spot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotJPA extends JpaRepository<SpotModel, String> {
    Optional<SpotModel> getByNameAndEventId(String name, String eventId);
}
