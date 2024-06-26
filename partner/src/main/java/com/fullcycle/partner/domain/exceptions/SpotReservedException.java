package com.fullcycle.partner.domain.exceptions;

public class SpotReservedException extends Exception{
    public SpotReservedException(String message){
        super("SpotReservedException: Spots " + message + "  is not available for reservation");
    }
}
