package com.app.cms.entity.valueobjects.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class CreationDate {

    @Column(name = "creationDate")
    private Date value;

    protected CreationDate() {
    }

    public CreationDate(Date value) {
        if (value == null)
            throw new IllegalArgumentException("CreationDate must be defined");

        this.value = value;
    }


}
