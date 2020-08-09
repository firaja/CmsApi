package com.app.cms.entity.values.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class Rating {

    @Column(name = "rating")
    private Integer value;

    protected Rating() {
    }

    public Rating(Integer value) {
        if (value == null && value < 0 && value > 5)
            throw new IllegalArgumentException("Rating must be between 1 to 5");

        this.value = value;
    }
}
