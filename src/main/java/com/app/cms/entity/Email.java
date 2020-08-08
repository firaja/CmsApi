package com.app.cms.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public class Email {

    @Column(name = "email")
    private String value;

    public Email() {
    }

    public Email(String value) {
        this.value = value;
    }


}
