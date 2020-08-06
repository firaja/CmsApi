package com.app.cms.validator;


public interface ValidatorOnSave<E> {
    void validateOnSave(E entity);
}
