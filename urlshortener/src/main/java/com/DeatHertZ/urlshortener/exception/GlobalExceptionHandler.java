package com.DeatHertZ.urlshortener.exception;

import com.DeatHertZ.urlshortener.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidUrl(InvalidUrlException exception)
    {
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(ShortCodeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidShortCode(ShortCodeNotFoundException exception)
    {
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}