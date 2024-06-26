package com.fullcycle.partner.domain.value_objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateDate {
    private LocalDateTime date;
    public ValidateDate(String date) throws Exception {
        this.date = validateDate(date);
    }

    private LocalDateTime validateDate(String value) throws Exception {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format", e);
        }

        LocalDateTime currentDate = LocalDateTime.now();
        if (dateTime.isBefore(currentDate)) {
            throw new Exception("Date of event cannot be a past date");
        }
        return dateTime;
    }
    public LocalDateTime getDate() {
        return date;
    }
}
