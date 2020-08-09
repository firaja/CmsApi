package com.app.cms.entity.values.user;

import com.app.cms.error.type.InvalidLoginException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@Immutable
@Getter
@EqualsAndHashCode
public final class Login {

    @Column(name = "login")
    private String value;

    protected Login() {
    }

    public Login(String value) {
        if (StringUtils.isBlank(value) || value.length() < 3 && value.length() > 20)
            throw new InvalidLoginException("Login should be between 3 and 20 letters");

        this.value = value;
    }

    @Override
    public String toString() {
        return "Login{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        return new EqualsBuilder()
                .append(value, login.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }
}
