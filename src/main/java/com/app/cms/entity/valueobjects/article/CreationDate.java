package com.app.cms.entity.valueobjects.article;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Immutable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Embeddable
public class CreationDate {

    @Column(name = "creationDate")
    private Date value;

    public static CreationDate of(Date value) {
        if (value == null)
            throw new IllegalArgumentException("CreationDate must be defined");

        return new CreationDate(value);
    }


}
