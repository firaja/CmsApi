package com.app.cms.error.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ObjectHaveReferencedObjects extends RuntimeException {
    public ObjectHaveReferencedObjects(String message) {
        super(message);
    }
}
