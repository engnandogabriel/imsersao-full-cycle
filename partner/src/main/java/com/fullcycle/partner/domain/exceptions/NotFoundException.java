package com.fullcycle.partner.domain.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super("NotFoundError: " + message);
    }
}
