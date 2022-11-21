package br.com.fogaca.pedidos.exception;

import java.util.Date;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }   
}
