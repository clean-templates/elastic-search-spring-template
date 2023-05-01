package com.rolandsall.elastic.search.spring.template.api.comment.advice;

import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PostDoesNotExistException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PostNotFoundException.class})
    protected ResponseEntity<Object> handle(PostNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
