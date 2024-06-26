package com.fullcycle.partner.infra.repository;

import com.fullcycle.partner.domain.entities.Event;
import com.fullcycle.partner.infra.models.Event.EventJPA;
import com.fullcycle.partner.infra.models.Event.EventModel;
import com.fullcycle.partner.services.repository.EventRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class EventDatabaseRepository implements EventRepository {
    private EventJPA eventJPA;

    public EventDatabaseRepository(EventJPA eventJPA) {
        this.eventJPA = eventJPA;
    }
    @Transactional
    @Override
    public void save(Event event) {
        EventModel eventModel = new EventModel(event.getId(), event.getName(), event.getDescription(), event.getDate(), event.getCreatedAt(), event.getUpdatedAt(), event.getPrice());
        this.eventJPA.save(eventModel);
    }
    @Transactional
    @Override
    public Optional<Event> getById(String id) throws Exception {
        EventModel eventModel = this.eventJPA.findById(id).orElse(null);
        if(eventModel == null) return Optional.empty();
        return Optional.of(Event.restore(eventModel.getId(), eventModel.getName(), eventModel.getDescription(), eventModel.getDate().toString(), eventModel.getCreatedAt(), eventModel.getUpdatedAt(), eventModel.getPrice()));
    }
    @Transactional
    @Override
    public void update(Event event) {
        EventModel eventModel = new EventModel(event.getId(), event.getName(), event.getDescription(), event.getDate(), event.getCreatedAt(), event.getUpdatedAt(), event.getPrice());
        this.eventJPA.save(eventModel);
    }
    @Transactional
    @Override
    public void delete(String id) {
        this.eventJPA.deleteById(id);
    }

}
