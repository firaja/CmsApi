package com.app.cms.valueobject.user;

import com.app.cms.error.type.InvalidEmailException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class EmailTest {

    @Test
    public void shouldCreateEmail() {
        // given, when
        var email = Email.of("email@mail.com");

        //then
        assertThat(email.getValue()).isEqualTo("email@mail.com");
    }

    @Test
    public void shouldNotCreateEmail_valueIsNull() {
        assertThatThrownBy(() ->
                Email.of(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateEmail_valueIsNotValid() {
        assertThatThrownBy(() ->
                Email.of("email@.com")
        ).isInstanceOf(InvalidEmailException.class);
    }

    @Test
    public void shouldNotCreateEmail_valueIsNotValid2() {
        assertThatThrownBy(() ->
                Email.of("@test.com")
        ).isInstanceOf(InvalidEmailException.class);
    }

    @Test
    public void shouldNotCreateEmail_valueIsNotValid3() {
        assertThatThrownBy(() ->
                Email.of("emailtest.com")
        ).isInstanceOf(InvalidEmailException.class);
    }
}
