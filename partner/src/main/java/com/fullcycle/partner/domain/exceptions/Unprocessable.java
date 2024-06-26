package com.fullcycle.partner.domain.exceptions;

public class Unprocessable extends Exception{
    public Unprocessable(String message){
        super("UnprocessableEntity: "+message);
    }
}
