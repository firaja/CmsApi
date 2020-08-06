package com.app.cms.validator;

import com.app.cms.error.type.PasswordNotContainsUpperAndLowercaseException;
import com.app.cms.error.type.PasswordTooLongException;
import com.app.cms.error.type.PasswordTooShortException;
import org.springframework.stereotype.Component;


@Component
public class PasswordValidator implements ValidatorOnSave<char[]> {

    @Override
    public void validateOnSave(char[] password) {
        if (password.length < 6) {
            throw new PasswordTooShortException("Password must be at least 6 characters");
        } else if (password.length > 20) {
            throw new PasswordTooLongException("Password must be 20 characters or less");
        } else if (!isContainsUpperAndLowerCase(password)) {
            throw new PasswordNotContainsUpperAndLowercaseException("Password must contain at least one lowercase and uppercase characters");
        }
    }

    boolean isContainsUpperAndLowerCase(char[] password) {
        int uppercaseCounter = 0;
        int lowercaseCounter = 0;

        for (int i = 0; i < password.length; i++) {
            if (Character.isUpperCase(password[i]))
                uppercaseCounter++;
            if (Character.isLowerCase(password[i]))
                lowercaseCounter++;
        }

        return uppercaseCounter > 0 && lowercaseCounter > 0 ? true : false;
    }

}
