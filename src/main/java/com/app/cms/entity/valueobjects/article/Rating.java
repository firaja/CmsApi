package com.app.cms.entity.valueobjects.article;

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
public class Rating {

    private static final long serialVersionUID = 6732775093033061190L;

    @Column(name = "ratingValue")
    private Float value;

    @Column(name = "ratingCount")
    private Integer count;

    public static Rating of(Float ratingValue, Integer ratingCount) {
        if (ratingValue == null || ratingCount == null)
            throw new IllegalArgumentException("Rating and rating count must be set");

        if (ratingValue < 0 || ratingValue > 5)
            throw new IllegalArgumentException("Rating must be between 1 to 5");

        if (ratingCount < 0)
            throw new IllegalArgumentException("Rating count must be more than 0");

        return new Rating(ratingValue, ratingCount);
    }

}
