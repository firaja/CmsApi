package com.app.cms.entity.valueobjects.user;

import com.app.cms.error.type.InvalidEmailException;
import lombok.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Immutable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Embeddable
public class Email {

    @Column(name = "email")
    private String value;

    public static Email of(String value) {
        if (!EmailValidator.getInstance().isValid(value))
            throw new InvalidEmailException("Email is not valid");

        return Email.of(value);
    }


}
