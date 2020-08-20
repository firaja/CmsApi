package com.app.cms.entity.values.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class Rating implements Serializable {

    private static final long serialVersionUID = 6732775093033061190L;


    @Column(name = "htrdfdfdf")
    private Float rrVal;

    @Column(name = "aratingCount")
    private Integer aratingCount;


    protected Rating() {
    }

    public Rating(Float value, Integer ratingCount) {
        if (value == null || ratingCount == null)
            throw new IllegalArgumentException("Rating and rating count must be set");

        if (value < 0 || value > 5)
            throw new IllegalArgumentException("Rating must be between 1 to 5");

        if (ratingCount < 0)
            throw new IllegalArgumentException("Rating count must be more than 0");

        this.rrVal = value;
        this.aratingCount = ratingCount;
    }


}
