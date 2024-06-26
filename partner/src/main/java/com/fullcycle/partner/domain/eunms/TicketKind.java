package com.fullcycle.partner.domain.eunms;

import com.fullcycle.partner.domain.exceptions.TicketKindException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TicketKind {
    FULL("full"),
    HALF("half");

    private final String kind;

    TicketKind(String kind) {
        this.kind = kind;
    }

    @JsonValue
    public String getKind() {
        return kind;
    }

    @JsonCreator
    public static TicketKind fromString(String kind) throws RuntimeException {
        for (TicketKind ticketKind : TicketKind.values()) {
            if (ticketKind.kind.equalsIgnoreCase(kind)) {
                return ticketKind;
            }
        }
        throw new TicketKindException();
    }
}