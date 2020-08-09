package com.app.cms.entity.values.comment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class Author {

    @Column(name = "author")
    private String value;

    public Author() {
    }

    public Author(String value) {
        if (value.length() > 150)
            throw new IllegalArgumentException("Maximal author length is 150 characters");

        this.value = value;
    }
}
