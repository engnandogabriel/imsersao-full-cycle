package com.fullcycle.partner.infra.repository;

import com.fullcycle.partner.domain.entities.Ticket;
import com.fullcycle.partner.infra.models.Spot.SpotModel;
import com.fullcycle.partner.infra.models.Ticket.TicketJPA;
import com.fullcycle.partner.infra.models.Ticket.TicketModel;
import com.fullcycle.partner.services.repository.TicketRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TicketDataBaseRepository implements TicketRepository {
    private TicketJPA ticketJPA;

    public TicketDataBaseRepository(TicketJPA ticketJPA) {
        this.ticketJPA = ticketJPA;
    }
    @Transactional
    @Override
    public void save(Ticket ticket) {
        SpotModel spotModel = new SpotModel(ticket.getSpotId());
        TicketModel ticketModel = new TicketModel(ticket.getId(), ticket.getEmail(), ticket.getTicketKind(), ticket.getCreatedAt(), ticket.getUpdatedAt(), spotModel);
        this.ticketJPA.save(ticketModel);
    }
}
