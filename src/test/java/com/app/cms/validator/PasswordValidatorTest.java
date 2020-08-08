package com.app.cms.validator;

import com.app.cms.entity.Password;
import com.app.cms.error.type.PasswordNotContainsUpperAndLowercaseException;
import com.app.cms.error.type.PasswordTooLongException;
import com.app.cms.error.type.PasswordTooShortException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PasswordValidatorTest {


    @Test
    public void shouldValidatePassword() {
        //given when
        var password = new Password(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd'});

        //then
        then(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldValidatePassword_6Letters() {
        //given when
        var password = new Password(new char[]{'P', 'a', 's', 's', 'w', 'o'});

        //then
        then(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldValidatePassword_20Letters() {
        //given when
        var password = new Password(new char[]{'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'});

        //then
        then(password.getValue()).isNotEmpty();
    }

    @Test
    public void shouldThrowError_passwordToShort() {
        //given when then
        assertThatThrownBy(() -> {
            new Password(new char[]{'P', 'a', 's', 's', 'w'});
        }).isInstanceOf(PasswordTooShortException.class);
    }

    @Test
    public void shouldThrowError_passwordToLong() {
        //given when then
        assertThatThrownBy(() -> {
            new Password(new char[]{'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', '4'});
        }).isInstanceOf(PasswordTooLongException.class);
    }

    @Test
    public void shouldThrowError_passwordNotContainsUppercase() {
        //given when then
        assertThatThrownBy(() -> {
            new Password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'});
        }).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

    @Test
    public void shouldThrowError_passwordNotContainsLowercase() {
        //given when then
        assertThatThrownBy(() -> {
            new Password(new char[]{'P', 'A', 'S', 'S', 'W', 'O', 'D', '1', '2', '3'});
        }).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

}
