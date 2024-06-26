package com.fullcycle.partner.infra.dao;

import com.fullcycle.partner.domain.dto.ResponseEventDTO;
import com.fullcycle.partner.infra.models.Event.EventJPA;
import com.fullcycle.partner.infra.models.Event.EventModel;
import com.fullcycle.partner.services.dao.EventDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventDatabaseDAO implements EventDAO {
    private EventJPA eventJPA;

    public EventDatabaseDAO(EventJPA eventJPA) {
        this.eventJPA = eventJPA;
    }

    @Override
    public List<ResponseEventDTO> findAll() {
        List<EventModel> eventModels = this.eventJPA.findAll();
        List<ResponseEventDTO> events = eventModels.stream()
                .map(event -> new ResponseEventDTO(
                        event.getId(),
                        event.getName(),
                        event.getDescription(),
                        event.getDate().toString(),
                        event.getPrice()
                ))
                .collect(Collectors.toList());
        return events;
    }

    @Override
    public Optional<ResponseEventDTO> findById(String id) {
        EventModel eventModel = this.eventJPA.findById(id).orElse(null);
        if (eventModel == null) return Optional.empty();
        ResponseEventDTO responseEventDTO = new ResponseEventDTO(eventModel.getId(), eventModel.getName(), eventModel.getDescription(), eventModel.getDate().toString(), eventModel.getPrice());
        return Optional.of(responseEventDTO);
    }
}
