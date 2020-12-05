package com.spring.codeblog.utils;

import org.springframework.http.HttpStatus;

public class Errors extends RuntimeException {

    private static final long serialVersionUID = -8615245851861457216L;

    private final HttpStatus httpStatus;

    public Errors(final String msg, final HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}