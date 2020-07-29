package com.app.cms.error.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameAlreadyExistsException extends RuntimeException  {



    public NameAlreadyExistsException(String message) {
        super(message);
    }



}
