package com.app.cms.valueobject.user;

import com.app.cms.error.type.InvalidLoginException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class LoginTest {

    @Test
    public void shouldCreateLogin() {
        // given, when
        var login = Login.of("login");

        //then
        assertThat(login.getValue()).isEqualTo("login");
    }

    @Test
    public void shouldCreateLogin_valueLength3() {
        // given, when
        var login = Login.of("abc");

        //then
        assertThat(login.getValue()).isEqualTo("abc");
    }

    @Test
    public void shouldCreateLogin_valueLength20() {
        // given, when
        var login = Login.of(new String(new char[20]));

        //then
        assertThat(login.getValue()).isEqualTo(new String(new char[20]));
    }

    @Test
    public void shouldNotCreateLogin_valueIsNull() {
        assertThatThrownBy(() ->
                Login.of(null)
        ).isInstanceOf(InvalidLoginException.class);
    }

    @Test
    public void shouldNotCreateLogin_valueIsTooShort() {
        assertThatThrownBy(() ->
                Login.of("ab")
        ).isInstanceOf(InvalidLoginException.class);
    }

    @Test
    public void shouldNotCreateLogin_valueIsTooLong() {
        assertThatThrownBy(() ->
                Login.of(new String(new char[21]))
        ).isInstanceOf(InvalidLoginException.class);
    }
}
