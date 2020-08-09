package com.app.cms.entity.values.comment;

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
        if (value == null || value.after(new Date()))
            throw new IllegalArgumentException("Creation date must be in the past");

        this.value = value;
    }
}
