package com.fullcycle.partner.infra.models.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJPA extends JpaRepository<TicketModel, String> {
}
