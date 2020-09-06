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
public class Content {

    @Column(name = "content")
    private String value;

    public static Content of(String value) {
        if (StringUtils.isBlank(value) || value.length() > 50000)
            throw new IllegalArgumentException("Content must be defined, max length is 50 000 characters");

        return new Content(value);
    }


}
