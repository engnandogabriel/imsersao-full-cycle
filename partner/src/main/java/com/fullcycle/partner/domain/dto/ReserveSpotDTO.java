package com.fullcycle.partner.domain.dto;

import com.fullcycle.partner.domain.eunms.TicketKind;

public record ReserveSpotDTO(String email, String[] names, TicketKind ticketKind) {
}
