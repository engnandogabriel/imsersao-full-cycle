package com.fullcycle.partner.domain.entities;

import com.fullcycle.partner.domain.eunms.StatusSpot;
import com.fullcycle.partner.domain.exceptions.Unprocessable;

import java.time.LocalDateTime;
import java.util.UUID;

public class Spot {
    private String id;
    private String name;
    private StatusSpot status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String eventId;

    private Spot(String id, String name, StatusSpot status, LocalDateTime createdAt, LocalDateTime updatedAt, String eventId) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.eventId = eventId;
    }

    public static Spot create(String name, String eventId) {
        String id = UUID.randomUUID().toString();
        return new Spot(id, name, StatusSpot.available, LocalDateTime.now(), LocalDateTime.now(), eventId);
    }

    public static Spot restore(String id, String name, StatusSpot status, LocalDateTime createdAt, LocalDateTime updatedAt, String eventId) {
        return new Spot(id, name, status, createdAt, updatedAt, eventId);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) this.name = name;
    }

    public StatusSpot getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setReserved() throws Exception {
        if (this.status.equals("reserved")) throw new Unprocessable("Status already is reserved");
        this.status = StatusSpot.reserved;
    }
}
