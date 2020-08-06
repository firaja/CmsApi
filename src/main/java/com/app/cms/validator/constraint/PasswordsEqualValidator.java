package com.app.cms.validator.constraint;

import com.app.cms.dto.UserPasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualValidator implements ConstraintValidator<PasswordsEqual, Object> {

    @Override
    public void initialize(PasswordsEqual arg0) {
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        UserPasswordDto user = (UserPasswordDto) candidate;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
