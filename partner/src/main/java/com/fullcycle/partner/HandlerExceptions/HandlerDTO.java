package com.fullcycle.partner.HandlerExceptions;

import org.springframework.http.HttpStatus;

public record HandlerDTO<T>(HttpStatus status, String message, T body) {

}
