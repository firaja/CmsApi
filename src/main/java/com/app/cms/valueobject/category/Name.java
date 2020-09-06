package com.app.cms.valueobject.category;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Immutable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Embeddable
public class Name {

    @Column(name = "name")
    private String value;

    public static Name of(String value) {
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("Maximal category is not set");

        if (value.length() > 150)
            throw new IllegalArgumentException("Maximal category name length is 150 characters");

        return new Name(value);
    }



}
