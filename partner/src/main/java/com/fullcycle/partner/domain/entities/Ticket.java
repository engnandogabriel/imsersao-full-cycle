package com.fullcycle.partner.domain.entities;

import com.fullcycle.partner.domain.eunms.TicketKind;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String id;
    private String email;
    private TicketKind ticketKind;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String spotId;


    private Ticket(String id, String email, TicketKind ticketKind, LocalDateTime createdAt, LocalDateTime updatedAt, String spotId) {
        this.id = id;
        this.email = email;
        this.ticketKind = ticketKind;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.spotId = spotId;
    }

    public static Ticket create(String email, TicketKind ticketKind, String spotId) {
        String id = UUID.randomUUID().toString();
        LocalDateTime time = LocalDateTime.now();
        return new Ticket(id, email, ticketKind, time, time, spotId);
    }

    public static Ticket restore(String id, String email, TicketKind ticketKind, LocalDateTime createdAt, LocalDateTime updatedAt, String spotId) {
        return new Ticket(id, email, ticketKind, createdAt, updatedAt, spotId);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TicketKind getTicketKind() {
        return ticketKind;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getSpotId() {
        return spotId;
    }
}
