package com.app.cms.valueobject.article;

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
public class Title {

    @Column(name = "title")
    private String value;

    public static Title of(String value) {
        if (StringUtils.isBlank(value) || value.length() > 200)
            throw new IllegalArgumentException("Title must be defined, max length 200 characters");

        return new Title(value);
    }
}
