package com.app.cms.entity.valueobjects.user;

import com.app.cms.error.type.InvalidEmailException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class Email {

    @Column(name = "email")
    private String value;

    public Email() {
    }

    public Email(String value) {
        if (!EmailValidator.getInstance().isValid(value))
            throw new InvalidEmailException("Email is not valid");

        this.value = value;
    }


}
