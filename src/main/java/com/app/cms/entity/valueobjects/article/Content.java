package com.app.cms.entity.valueobjects.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class Content {

    @Column(name = "content")
    private String value;

    protected Content() {
    }

    public Content(String value) {
        if (StringUtils.isBlank(value) || value.length() > 50000)
            throw new IllegalArgumentException("Content must be defined, max length is 50 000 characters");

        this.value = value;
    }


}
