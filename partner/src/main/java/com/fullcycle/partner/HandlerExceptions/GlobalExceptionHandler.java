package com.fullcycle.partner.HandlerExceptions;

import com.fullcycle.partner.domain.exceptions.TicketKindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketKindException.class)
    public ResponseEntity<HandlerDTO> handleUnprocessableException(TicketKindException e, WebRequest request) {
        HandlerDTO handlerDTO = new Handlers<>().unprocessable(e);
        return new ResponseEntity<>(handlerDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
