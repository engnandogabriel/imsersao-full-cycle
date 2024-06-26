package com.fullcycle.partner.domain.exceptions;
public class TicketKindException extends RuntimeException {
    public TicketKindException(){
        super("Ticket kind is invalid");
    }
}
