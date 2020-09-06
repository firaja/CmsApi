package com.app.cms.valueobject.user;

import com.app.cms.error.type.PasswordNotContainsUpperAndLowercaseException;
import com.app.cms.error.type.PasswordTooLongException;
import com.app.cms.error.type.PasswordTooShortException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
public class PasswordTest {

    @Test
    public void shouldCreatePassword() {
        // given, when
        var password = Password.of("Pass12".toCharArray(), "Pass12".toCharArray());

        //then
        assertThat(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldCreatePassword2_valueLength20() {
        // given, when
        var password = Password.of("Pass123Pass123Pass12".toCharArray(), "Pass123Pass123Pass12".toCharArray());

        //then
        assertThat(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldNotCreatePassword_valueIsTooShort() {
        assertThatThrownBy(() ->
                Password.of("Pass1".toCharArray(), "Pass1".toCharArray())
        ).isInstanceOf(PasswordTooShortException.class);
    }

    @Test
    public void shouldNotCreatePassword_valueIsTooLong() {
        assertThatThrownBy(() ->
                Password.of("Pass123Pass123Pass123".toCharArray(), "Pass123Pass123Pass123".toCharArray())
        ).isInstanceOf(PasswordTooLongException.class);
    }

    @Test
    public void shouldNotCreatePassword_valueNotContainsLowercase() {
        assertThatThrownBy(() ->
                Password.of("PASSWORD".toCharArray(), "PASSWORD".toCharArray())
        ).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

    @Test
    public void shouldNotCreatePassword_valueNotContainsUppercase() {
        assertThatThrownBy(() ->
                Password.of("password".toCharArray(), "password".toCharArray())
        ).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

    @Test
    public void shouldCreatePassword_6Letters() {
        //given
        var password = Password.of(new char[]{'P', 'a', 's', 's', 'w', 'o'}, new char[]{'P', 'a', 's', 's', 'w', 'o'});

        //then
        then(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldCreatePassword_20Letters() {
        //given
        var password = Password.of(new char[]{'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'},
                new char[]{'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'});

        //then
        then(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldNotCreatePassword_passwordToShort() {
        AssertionsForClassTypes.assertThatThrownBy(() -> {
            Password.of(new char[]{'P', 'a', 's', 's', 'w'}, new char[]{'P', 'a', 's', 's', 'w'});
        }).isInstanceOf(PasswordTooShortException.class);
    }

    @Test
    public void shouldNotCreatePassword_passwordToLong() {
        AssertionsForClassTypes.assertThatThrownBy(() -> {
            Password.of(new char[]{'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', '4'},
                    new char[]{'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', '4'});
        }).isInstanceOf(PasswordTooLongException.class);
    }

    @Test
    public void shouldNotCreatePassword_notContainsUppercase() {
        AssertionsForClassTypes.assertThatThrownBy(() -> {
            Password.of(new char[]{'p', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'}, new char[]{'p', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'});
        }).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

    @Test
    public void shouldNotCreatePassword_notContainsLowercase() {
        AssertionsForClassTypes.assertThatThrownBy(() -> {
            Password.of(new char[]{'P', 'A', 'S', 'S', 'W', 'O', 'D', '1', '2', '3'}, new char[]{'P', 'A', 'S', 'S', 'W', 'O', 'D', '1', '2', '3'});
        }).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }
}
