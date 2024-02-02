package com.devlog.core.config.exception;

import org.springframework.http.HttpStatus;

public class WebException extends RuntimeException {
    private final HttpStatus httpStatus;

    public WebException(String message) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    public WebException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public WebException(HttpStatus httpStatus, String message, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
