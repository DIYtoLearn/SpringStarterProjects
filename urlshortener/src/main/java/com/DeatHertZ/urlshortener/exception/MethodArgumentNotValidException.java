package com.DeatHertZ.urlshortener.exception;

public class MethodArgumentNotValidException extends RuntimeException {

    public MethodArgumentNotValidException(String message){
        super(message);
    }

}
