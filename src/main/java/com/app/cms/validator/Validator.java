package com.app.cms.validator;


public interface Validator<E> {
    public void validate(E entity);
}
