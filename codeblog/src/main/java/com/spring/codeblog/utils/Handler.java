package com.spring.codeblog.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> methodArgumentNotValidException(
            MethodArgumentNotValidException r) {

        Map<String, String> errors = new HashMap<>();

        r.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        Response<Map<String, String>> response = new Response<>(Boolean.FALSE);

        response.setData(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Errors.class)
    public ResponseEntity<Response<String>> handlerReaderException(Errors r) {

        Response<String> response = new Response<>(Boolean.FALSE);

        response.setData(r.getMessage());

        return ResponseEntity.status(r.getHttpStatus()).body(response);
    }
}