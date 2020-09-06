package com.app.cms.repository;

import com.app.cms.valueobject.user.Email;
import com.app.cms.valueobject.user.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldUpdateUserEmail() {
        //given
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("email", "email23556@email.com");

        //when
        userRepository.updatePartially(-1L, userValues);

        //then
        then(userRepository.getOne(-1L).getEmail()).isEqualTo(Email.of("email23556@email.com"));
    }

    @Test
    public void shouldUpdateUserEmailAndPassword() {
        //given
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("email", "email3434@email.com");
        userValues.put("password", "Password4699304");
        userValues.put("passwordConfirm", "Password4699304");

        //when
        userRepository.updatePartially(-1L, userValues);

        //then
        then(userRepository.getOne(-1L).getEmail()).isEqualTo(Email.of("email3434@email.com"));
        then(userRepository.getOne(-1L).getPassword()).isEqualTo(Password.of("Password4699304".toCharArray(), "Password4699304".toCharArray()));
    }


}
