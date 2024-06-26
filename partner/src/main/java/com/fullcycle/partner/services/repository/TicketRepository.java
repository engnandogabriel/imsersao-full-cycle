package com.fullcycle.partner.services.repository;

import com.fullcycle.partner.domain.entities.Ticket;

public interface TicketRepository {
    void save(Ticket ticket);
}
