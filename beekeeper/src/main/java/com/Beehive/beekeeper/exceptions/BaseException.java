package com.Beehive.beekeeper.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class BaseException extends RuntimeException{
    private HttpStatus status;
    private Response response;

    public BaseException(HttpStatus status, Response response) {
        this.status = status;
        this.response = response;
    }
}
