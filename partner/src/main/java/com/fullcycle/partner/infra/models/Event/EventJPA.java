package com.fullcycle.partner.infra.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJPA extends JpaRepository<EventModel, String>{
}
