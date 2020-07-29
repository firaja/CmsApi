package com.app.cms.error.advice;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ServiceErrorAdvice {

    @ExceptionHandler({NameAlreadyExistsException.class})
    public  @ResponseBody ResponseEntity<Object> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.CONFLICT, e);
    }

    private ResponseEntity<Object> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).headers(new HttpHeaders()).body(e.getMessage());
    }
}
