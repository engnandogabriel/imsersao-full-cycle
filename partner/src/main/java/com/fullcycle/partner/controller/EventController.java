package com.fullcycle.partner.controller;

import com.fullcycle.partner.HandlerExceptions.HandlerDTO;
import com.fullcycle.partner.domain.dto.CreateEventDTO;
import com.fullcycle.partner.domain.dto.UpdateEventDTO;
import com.fullcycle.partner.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "events")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<HandlerDTO> post(@RequestBody CreateEventDTO createEventDTO) {
        HandlerDTO output = this.eventService.save(createEventDTO);
        return new ResponseEntity<>(output, output.status());
    }

    @GetMapping
    public ResponseEntity<HandlerDTO> findAll() {
        HandlerDTO output = this.eventService.findAll();
        return new ResponseEntity<>(output, output.status());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<HandlerDTO> getById(@PathVariable String id) {
        HandlerDTO output = this.eventService.getById(id);
        return new ResponseEntity<>(output, output.status());
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<HandlerDTO> update(@PathVariable String id, @RequestBody UpdateEventDTO updateEventDTO) {
        HandlerDTO output = this.eventService.update(id, updateEventDTO);
        return new ResponseEntity<>(output, output.status());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<HandlerDTO> update(@PathVariable String id) {
        HandlerDTO output = this.eventService.delete(id);
        return new ResponseEntity<>(output, output.status());
    }
}
