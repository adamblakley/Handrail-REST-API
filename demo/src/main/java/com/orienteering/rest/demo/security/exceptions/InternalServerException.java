package com.orienteering.rest.demo.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Standard internal server response, unimplements
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    /**
     * Returns internal server error exception
     * @param message
     */
    public InternalServerException(String message) {
        super(message);
    }

    /**
     * returns internal server error exception with throwable
     * @param message
     * @param throwable
     */
    public InternalServerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
