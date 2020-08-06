package com.app.cms.validator;

import com.app.cms.error.type.PasswordNotContainsUpperAndLowercaseException;
import com.app.cms.error.type.PasswordTooLongException;
import com.app.cms.error.type.PasswordTooShortException;
import com.app.cms.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PasswordValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PasswordValidator passwordValidator;

    @Test
    public void shouldValidatePassword() {
        //given
        char[] password = {'P', 'a', 's', 's', 'w', 'o', 'r', 'd'};

        //when
        passwordValidator.validateOnSave(password);
    }

    @Test
    public void shouldValidatePassword_6Letters() {
        //given
        char[] password = {'P', 'a', 's', 's', 'w', 'o'};

        //when
        passwordValidator.validateOnSave(password);
    }

    @Test
    public void shouldValidatePassword_20Letters() {
        //given
        char[] password = {'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'};

        //when
        passwordValidator.validateOnSave(password);
    }

    @Test
    public void shouldThrowError_passwordToShort() {
        //given
        char[] password = {'P', 'a', 's', 's', 'w'};

        //when then
        assertThatThrownBy(() -> {
            passwordValidator.validateOnSave(password);
        }).isInstanceOf(PasswordTooShortException.class);
    }

    @Test
    public void shouldThrowError_passwordToLong() {
        //given
        char[] password = {'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', 'P', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3', '4'};

        //when then
        assertThatThrownBy(() -> {
            passwordValidator.validateOnSave(password);
        }).isInstanceOf(PasswordTooLongException.class);
    }

    @Test
    public void shouldThrowError_passwordNotContainsUppercase() {
        //given
        char[] password = {'p', 'a', 's', 's', 'w', 'o', 'd', '1', '2', '3'};

        //when then
        assertThatThrownBy(() -> {
            passwordValidator.validateOnSave(password);
        }).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

    @Test
    public void shouldThrowError_passwordNotContainsLowercase() {
        //given
        char[] password = {'P', 'A', 'S', 'S', 'W', 'O', 'D', '1', '2', '3'};

        //when then
        assertThatThrownBy(() -> {
            passwordValidator.validateOnSave(password);
        }).isInstanceOf(PasswordNotContainsUpperAndLowercaseException.class);
    }

}
