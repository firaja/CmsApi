package com.app.cms.valueobject.comment;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Immutable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Embeddable
public class Author {

    @Column(name = "author")
    private String value;

    public static Author of(String value) {

        if (value == null)
            throw new IllegalArgumentException("Author value must be set");

        if (value.length() > 150)
            throw new IllegalArgumentException("Maximal author length is 150 characters");

        return new Author(value);
    }
}
