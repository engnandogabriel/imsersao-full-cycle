package com.fullcycle.partner.domain.entities;

import com.fullcycle.partner.domain.value_objects.Price;
import com.fullcycle.partner.domain.value_objects.ValidateDate;
import com.fullcycle.partner.domain.value_objects.Name;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    private String id;
    private String name;
    private String description;
    private LocalDateTime date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double price;


    private Event(String id, String name, String description, LocalDateTime date, LocalDateTime createdAt, LocalDateTime updatedAt, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.price = price;
    }

    public static Event create(String name, String description, String date, Double price) throws Exception {
        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        return new Event(id, new Name(name).getName(), description, new ValidateDate(date).getDate(), createdAt, updatedAt, new Price(price).getPrice());
    }

    public static Event restore(String id, String name, String description, String date, LocalDateTime createdAt, LocalDateTime updatedAt, Double price) throws Exception {
        return new Event(id, new Name(name).getName(), description, new ValidateDate(date).getDate(), createdAt, updatedAt, new Price(price).getPrice());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name != null && !name.isEmpty()) this.name = new Name(name).getName();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(String date) throws Exception {
        if (date != null && !date.isEmpty()) this.date = new ValidateDate(date).getDate();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) throws Exception {
        if (price != null) this.price = new Price(price).getPrice();
    }
}
