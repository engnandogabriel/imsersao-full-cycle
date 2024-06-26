package com.fullcycle.partner.infra.models.ReservationHistory;

import com.fullcycle.partner.domain.eunms.StatusReservation;
import com.fullcycle.partner.domain.eunms.TicketKind;
import com.fullcycle.partner.infra.models.Spot.SpotModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ReservationHistory", schema = "partners")
public class ReservationHistoryModel {
    @Id
    @Column(name = "id", length = 191, nullable = false)
    private String id;

    @Column(name = "email", length = 191, nullable = false)
    private String email;

    @Column(name = "ticketKind")
    @Enumerated(EnumType.STRING)
    private TicketKind ticketKind;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusReservation status;

    @Column(name = "createdAt", nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    @Column(name = "updateAt", nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spotId", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "ReservationHistory_spotId_fkey"))
    private SpotModel spot; // Referência para SpotModel usando spotId como String


    public ReservationHistoryModel() {
    }

    public ReservationHistoryModel(String id, String email, TicketKind ticketKind, StatusReservation status, LocalDateTime createdAt, LocalDateTime updatedAt, SpotModel spotId) {
        this.id = id;
        this.email = email;
        this.ticketKind = ticketKind;
        this.status = status;
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

    public StatusReservation getStatus() {
        return status;
    }

    public void setStatus(StatusReservation status) {
        this.status = status;
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
