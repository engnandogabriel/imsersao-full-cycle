package com.fullcycle.partner.infra.models.Ticket;


import com.fullcycle.partner.domain.eunms.TicketKind;
import com.fullcycle.partner.infra.models.Spot.SpotModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket", schema = "partners")
public class TicketModel {
    @Id
    @Column(name = "id", length = 191, nullable = false)
    private String id;

    @Column(name = "email", length = 191, nullable = false)
    private String email;

    @Column(name = "ticketKind")
    @Enumerated(EnumType.STRING)
    private TicketKind ticketKind;

    @Column(name="createdAt", nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt",nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "spotId",   referencedColumnName = "id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "Ticket_spotId_fkey"))
    private SpotModel spot;


    public TicketModel() {
    }

    public TicketModel(String id, String email, TicketKind ticketKind, LocalDateTime createdAt, LocalDateTime updatedAt, SpotModel spotId) {
        this.id = id;
        this.email = email;
        this.ticketKind = ticketKind;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.spot = spotId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TicketKind getTicketKind() {
        return ticketKind;
    }

    public void setTicketKind(TicketKind ticketKind) {
        this.ticketKind = ticketKind;
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

    public SpotModel getSpot() {
        return spot;
    }

    public void setSpot(SpotModel spotId) {
        this.spot = spotId;
    }
}
