package com.javainuse.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends GatewayException {

    public BadRequestException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
