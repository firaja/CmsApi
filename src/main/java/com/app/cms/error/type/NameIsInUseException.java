package com.app.cms.error.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameIsInUseException extends RuntimeException  {
    public NameIsInUseException(String message) {
        super(message);
    }
}
