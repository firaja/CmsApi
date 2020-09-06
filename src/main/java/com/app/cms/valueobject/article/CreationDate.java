package com.app.cms.valueobject.article;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Immutable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Embeddable
public class CreationDate {

    @Column(name = "creationDate")
    private LocalDate value;

    public static CreationDate of(LocalDate value) {
        if (value == null)
            throw new IllegalArgumentException("CreationDate must be defined");

        return new CreationDate(value);
    }


}
