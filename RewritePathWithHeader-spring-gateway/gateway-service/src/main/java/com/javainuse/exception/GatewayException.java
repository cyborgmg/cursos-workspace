package com.javainuse.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class GatewayException extends RuntimeException {

    private HttpStatus statusCode;

    public GatewayException(String message, HttpStatus statusCode) {
        super(MessageFormat.format("httpStatus={0} {1}", statusCode.value(), message));
        this.statusCode = statusCode;
    }

    public GatewayException(Throwable cause, HttpStatus statusCode) {
        super(MessageFormat.format("httpStatus={0}", statusCode.value()), cause);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
