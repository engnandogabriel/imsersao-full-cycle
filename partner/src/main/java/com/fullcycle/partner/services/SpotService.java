package com.fullcycle.partner.services;

import com.fullcycle.partner.HandlerExceptions.HandlerDTO;
import com.fullcycle.partner.HandlerExceptions.Handlers;
import com.fullcycle.partner.domain.dto.CreateSpotDTO;
import com.fullcycle.partner.domain.dto.ReserveSpotDTO;
import com.fullcycle.partner.domain.entities.Event;
import com.fullcycle.partner.domain.entities.ReservationHistory;
import com.fullcycle.partner.domain.entities.Spot;
import com.fullcycle.partner.domain.entities.Ticket;
import com.fullcycle.partner.domain.eunms.StatusSpot;
import com.fullcycle.partner.domain.exceptions.NotFoundException;
import com.fullcycle.partner.domain.exceptions.SpotReservedException;
import com.fullcycle.partner.domain.exceptions.Unprocessable;
import com.fullcycle.partner.services.repository.EventRepository;
import com.fullcycle.partner.services.repository.ReservationHistoryRepository;
import com.fullcycle.partner.services.repository.SpotRepository;
import com.fullcycle.partner.services.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpotService {
    private SpotRepository spotRepository;
    private EventRepository eventRepository;
    private ReservationHistoryRepository reservationHistoryRepository;
    private TicketRepository ticketRepository;

    public SpotService(SpotRepository spotRepository, EventRepository eventRepository, ReservationHistoryRepository reservationHistoryRepository, TicketRepository ticketRepository) {
        this.spotRepository = spotRepository;
        this.eventRepository = eventRepository;
        this.reservationHistoryRepository = reservationHistoryRepository;
        this.ticketRepository = ticketRepository;
    }

    public HandlerDTO save(String enventId, CreateSpotDTO createSpotDTO) {
        try {
            Event event = this.eventRepository.getById(enventId).orElseThrow(() -> new NotFoundException("Event not found"));
            Spot spot = Spot.create(createSpotDTO.name(), event.getId());
            this.spotRepository.save(spot);
            return new Handlers<>().success(spot);
        } catch (RuntimeException e) {
            return new Handlers<>().servrError(e);
        } catch (NotFoundException e) {
            return new Handlers<>().notFound(e);
        } catch (Unprocessable e) {
            return new Handlers<>().unprocessable(e);
        } catch (Exception e) {
            return new Handlers<>().badRequest(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public HandlerDTO reserve(String eventId, ReserveSpotDTO reserveSpotDTO) {
        try {
            List<Spot> spotList = new ArrayList<>();
            List<ReservationHistory> reservationHistoryList = new ArrayList<>();
            List<String> spotNotFoundList = new ArrayList<>();
            Event event = this.eventRepository.getById(eventId).orElseThrow(() -> new NotFoundException("Event not found"));

            for (String name : reserveSpotDTO.names()) {
                Spot spot = this.spotRepository.getByNameAndEventId(name, eventId).orElse(null);
                if (spot == null) spotNotFoundList.add(name);
                else spotList.add(spot);
            }

            if (!spotNotFoundList.isEmpty())
                throw new NotFoundException("Spots not exists: " + String.join(", ", spotNotFoundList));

            List<Ticket> ticketList = spotList.stream()
                    .map(spot -> Ticket.create(reserveSpotDTO.email(), reserveSpotDTO.ticketKind(), spot.getId()))
                    .collect(Collectors.toList());

            for (Spot spot : spotList) {
                if (spot.getStatus() == StatusSpot.reserved)
                    throw new SpotReservedException(spot.getName());
                spot.setReserved();
                reservationHistoryList.add(ReservationHistory.create(reserveSpotDTO.email(), reserveSpotDTO.ticketKind(), spot.getId()));
            }

            for (int i = 0; i < spotList.size(); i++) {
                this.spotRepository.save(spotList.get(i));
                this.reservationHistoryRepository.save(reservationHistoryList.get(i));
                this.ticketRepository.save(ticketList.get(i));
            }

            return new Handlers<>().success(ticketList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Handlers<>().servrError(e);
        } catch (NotFoundException e) {
            return new Handlers<>().notFound(e);
        } catch (Unprocessable | SpotReservedException e) {
            return new Handlers<>().unprocessable(e);
        } catch (Exception e) {
            return new Handlers<>().badRequest(e);
        }
    }
}
