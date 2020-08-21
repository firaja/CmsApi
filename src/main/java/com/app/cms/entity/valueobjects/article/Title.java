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
public class Title {

    @Column(name = "title")
    private String value;

    protected Title() {
    }

    public Title(String value) {
        if (StringUtils.isBlank(value) || value.length() > 200)
            throw new IllegalArgumentException("Title must be defined, max length 200 characters");

        this.value = value;
    }
}
