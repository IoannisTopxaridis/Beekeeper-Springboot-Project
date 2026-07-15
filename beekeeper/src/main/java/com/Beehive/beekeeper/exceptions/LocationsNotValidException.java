package com.Beehive.beekeeper.exceptions;

import org.springframework.http.HttpStatus;

public class LocationsNotValidException extends BaseException{

    public LocationsNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new Response(message));
    }
}
