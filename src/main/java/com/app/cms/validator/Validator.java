package com.app.cms.validator;


public interface Validator<E> {
     void validate(E entity);

     void validateOnDelete(Long id);
}
