package com.fullcycle.partner.domain.entities;

import com.fullcycle.partner.domain.eunms.StatusReservation;
import com.fullcycle.partner.domain.eunms.TicketKind;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationHistory {
    private String id;
    private String email;
    private TicketKind ticketKind;
    private StatusReservation status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String spotId;

    private ReservationHistory(String id, String email, TicketKind ticketKind, StatusReservation status, LocalDateTime createdAt, LocalDateTime updatedAt, String spotId) {
        this.id = id;
        this.email = email;
        this.ticketKind = ticketKind;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.spotId = spotId;
    }

    public static ReservationHistory create(String email, TicketKind ticketKind, String spotId) throws Exception {
        String id = UUID.randomUUID().toString();
        LocalDateTime time = LocalDateTime.now();
        return new ReservationHistory(id, email, ticketKind, StatusReservation.reserved, time, time, spotId);
    }

    public static ReservationHistory restore(String id, String email, TicketKind ticketKind, StatusReservation status, LocalDateTime createdAt, LocalDateTime updatedAt, String spotId) {
        return new ReservationHistory(id, email, ticketKind, status, createdAt, updatedAt, spotId);
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


    public StatusReservation getStatus() {
        return status;
    }

    public void reserveTicket() {
        this.status = StatusReservation.reserved;
    }

    public void cancelTicket() {
        this.status = StatusReservation.canceled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSpotId() {
        return spotId;
    }

}

