package com.app.cms.error.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordNotContainsUpperAndLowercaseException extends RuntimeException {
    public PasswordNotContainsUpperAndLowercaseException(String message) {
        super(message);
    }
}
