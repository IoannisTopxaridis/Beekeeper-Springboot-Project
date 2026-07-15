package com.Beehive.beekeeper.exceptions;

import org.springframework.http.HttpStatus;

public class LocationNotFoundException extends BaseException{
    public LocationNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new Response("Location Not Found"));
    }
}
