package com.fullcycle.partner.services;

import com.fullcycle.partner.HandlerExceptions.HandlerDTO;
import com.fullcycle.partner.HandlerExceptions.Handlers;
import com.fullcycle.partner.domain.dto.CreateEventDTO;
import com.fullcycle.partner.domain.dto.ResponseEventDTO;
import com.fullcycle.partner.domain.dto.UpdateEventDTO;
import com.fullcycle.partner.domain.entities.Event;
import com.fullcycle.partner.domain.exceptions.NotFoundException;
import com.fullcycle.partner.domain.exceptions.Unprocessable;
import com.fullcycle.partner.services.dao.EventDAO;
import com.fullcycle.partner.services.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;
    private EventDAO eventDAO;

    public EventService(EventRepository eventRepository, EventDAO eventDAO) {
        this.eventRepository = eventRepository;
        this.eventDAO = eventDAO;
    }

    public HandlerDTO save(CreateEventDTO createEventDTO) {
        try {
            Event event = Event.create(createEventDTO.name(), createEventDTO.description(), createEventDTO.date(), createEventDTO.price());
            this.eventRepository.save(event);
            return new Handlers<>().success(event);
        } catch (RuntimeException e) {
            return new Handlers<>().servrError(e);
        } catch (Unprocessable e) {
            return new Handlers<>().unprocessable(e);
        } catch (Exception e) {
            return new Handlers<>().badRequest(e);
        }
    }

    public HandlerDTO findAll() {
        try {
            List<ResponseEventDTO> responseEventDTOList = this.eventDAO.findAll();
            return new Handlers<>().success(responseEventDTOList);
        } catch (RuntimeException e) {
            return new Handlers<>().servrError(e);
        } catch (Exception e) {
            return new Handlers<>().badRequest(e);
        }
    }

    public HandlerDTO getById(String id) {
        try {
            ResponseEventDTO eventDAO = this.eventDAO.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));
            return new Handlers<>().success(eventDAO);
        } catch (RuntimeException e) {
            return new Handlers<>().servrError(e);
        } catch (NotFoundException e) {
            return new Handlers<>().notFound(e);
        } catch (Exception e) {
            return new Handlers<>().badRequest(e);
        }
    }

    public HandlerDTO update(String id, UpdateEventDTO updateEventDTO) {
        try {
            Event event = this.eventRepository.getById(id).orElseThrow(() -> new NotFoundException("Event Not Found"));
            event.setName(updateEventDTO.name());
            event.setDescription(updateEventDTO.description());
            event.setDate(updateEventDTO.date());
            event.setPrice(updateEventDTO.price());
            event.setUpdatedAt();
            this.eventRepository.update(event);
            return new Handlers<>().success(event);
        } catch (RuntimeException e) {
            return new Handlers<>().servrError(e);
        } catch (NotFoundException e) {
            return new Handlers<>().notFound(e);
        } catch (Unprocessable e) {
            return new Handlers<>().unprocessable(e);
        } catch (Exception e) {
            return new Handlers<>().unprocessable(e);
        }
    }

    public HandlerDTO delete(String id) {
        try {
            Event event = this.eventRepository.getById(id).orElseThrow(() -> new NotFoundException("Event Not Found"));
            this.eventRepository.delete(event.getId());
            return new Handlers<>().success("Deleted");
        } catch (RuntimeException e) {
            return new Handlers<>().servrError(e);
        } catch (NotFoundException e) {
            return new Handlers<>().notFound(e);
        } catch (Exception e) {
            return new Handlers<>().unprocessable(e);
        }
    }
}
