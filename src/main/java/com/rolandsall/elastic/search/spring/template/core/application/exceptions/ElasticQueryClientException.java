package com.rolandsall.elastic.search.spring.template.core.application.exceptions;

public class ElasticQueryClientException extends RuntimeException{

    public ElasticQueryClientException() {
    }

    public ElasticQueryClientException(String message) {
        super(message);
    }

    public ElasticQueryClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
