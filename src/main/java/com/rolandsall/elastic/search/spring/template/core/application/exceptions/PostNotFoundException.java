package com.rolandsall.elastic.search.spring.template.core.application.exceptions;

public class PostNotFoundException extends Exception{

    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
