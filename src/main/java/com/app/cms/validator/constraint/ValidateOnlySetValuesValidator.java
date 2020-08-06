package com.app.cms.validator.constraint;

import com.app.cms.dto.UserPartiallyUpdateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateOnlySetValuesValidator implements ConstraintValidator<ValidateOnlySetValues, Object> {

    @Override
    public void initialize(ValidateOnlySetValues arg0) {
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        var user = (UserPartiallyUpdateDto) candidate;


        return user.getPassword().equals(user.getPasswordConfirm());
    }
}

