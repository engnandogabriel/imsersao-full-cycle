package com.fullcycle.partner.infra.models.Spot;

import com.fullcycle.partner.domain.eunms.StatusSpot;
import com.fullcycle.partner.infra.models.Event.EventModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Spot", schema = "partners")
public class SpotModel {
    @Id
    @Column(name = "id", length = 191, nullable = false)
    private String id;

    @Column(name = "name", length = 191, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSpot status;

    @Column(name = "createdAt", nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "eventId",  referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "Spot_eventId_fkey"))
    private EventModel event;



    public SpotModel() {
    }
    public SpotModel(String id){
        this.id = id;
    }

    public SpotModel(String id, String name, StatusSpot status, LocalDateTime createdAt, LocalDateTime updatedAt, EventModel eventId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.event = eventId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public StatusSpot getStatus() {
        return status;
    }

    public void setStatus(StatusSpot status) {
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

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel eventId) {
        this.event = eventId;
    }
}
