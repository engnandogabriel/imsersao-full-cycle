package com.fullcycle.partner.controller;

import com.fullcycle.partner.HandlerExceptions.HandlerDTO;
import com.fullcycle.partner.domain.dto.CreateSpotDTO;
import com.fullcycle.partner.domain.dto.ReserveSpotDTO;
import com.fullcycle.partner.services.SpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "events")
public class SpotController {
    private SpotService spotService;
    public SpotController(SpotService spotService){
        this.spotService = spotService;
    }
    @PostMapping("{eventId}/spots")
    public ResponseEntity<HandlerDTO> save(@PathVariable String eventId, @RequestBody CreateSpotDTO createSpotDTO){
        HandlerDTO output = this.spotService.save(eventId, createSpotDTO);
        return new ResponseEntity<>(output, output.status());
    }
    @PostMapping("{eventId}/reserve")
    public ResponseEntity<HandlerDTO> reserve(@PathVariable String eventId, @RequestBody ReserveSpotDTO reserveSpotDTO){
        HandlerDTO output = this.spotService.reserve(eventId, reserveSpotDTO);
        return new ResponseEntity<>(output, output.status());
    }
}
