package com.app.cms.entity.valueobjects.category;

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
public class Name {

    @Column(name = "name")
    private String value;

    protected Name() {
    }

    public Name(String value) {
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("Maximal category is not set");

        if (value.length() > 150)
            throw new IllegalArgumentException("Maximal category name length is 150 characters");

        this.value = value;
    }



}
