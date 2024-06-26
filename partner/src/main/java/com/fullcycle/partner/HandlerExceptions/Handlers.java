package com.fullcycle.partner.HandlerExceptions;

import org.springframework.http.HttpStatus;

public class Handlers<T> {
    public HandlerDTO unprocessable(Exception e) {
        return new HandlerDTO<String>(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), null);
    }
    public HandlerDTO badRequest(Exception e){
        return new HandlerDTO<String>(HttpStatus.BAD_REQUEST,  e.getMessage(), null);
    }
    public HandlerDTO servrError(Exception e) {
        return new HandlerDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR, "ServerError", "Internal Server Error: Please, try again later");
    }
    public HandlerDTO notFound(Exception e) {
        return new HandlerDTO<String>(HttpStatus.NOT_FOUND,  e.getMessage(), null);
    }
    public HandlerDTO success(T obj) {
        return new HandlerDTO<T>(HttpStatus.OK, "Success", obj);
    }
}
