package com.app.cms.entity;

import com.app.cms.error.type.PasswordNotContainsUpperAndLowercaseException;
import com.app.cms.error.type.PasswordTooLongException;
import com.app.cms.error.type.PasswordTooShortException;
import com.app.cms.error.type.PasswordsAreNotSameException;
import com.password4j.Hash;
import com.password4j.SecureString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Arrays;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public final class Password {

    @Column(name = "password")
    private char[] value;

    protected Password() {
    }

    public Password(char[] value) {
        validate(value);

        this.value = hashPass(value);
    }

    public Password(char[] password, char[] passwordConfirm) {
        if (!Arrays.equals(password, passwordConfirm))
            throw new PasswordsAreNotSameException("Passwords are different");

        validate(password);

        this.value = hashPass(password);
    }

    private void validate(char[] password) {
        if (password.length < 6) {
            throw new PasswordTooShortException("Password must be at least 6 characters");
        } else if (password.length > 20) {
            throw new PasswordTooLongException("Password must be 20 characters or less");
        } else if (!isContainsUpperAndLowerCase(password)) {
            throw new PasswordNotContainsUpperAndLowercaseException("Password must contain at least one lowercase and uppercase characters");
        }
    }

    private boolean isContainsUpperAndLowerCase(char[] password) {
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

    private char[] hashPass(char[] password) {
        SecureString securedPassword = new SecureString(password);
        Hash hash = com.password4j.Password.hash(securedPassword).addSalt("4jvd8343j4f23mc").withPBKDF2();

        return hash.getResult().toCharArray();
    }

}
