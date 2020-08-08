package com.app.cms.repository;

import com.app.cms.entity.Password;
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
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", "newEmail@mail.com");

        //when
        userRepository.updatePartially(-1L, userData);

        //then
        then(userRepository.getOne(-1L).getEmail()).isEqualTo("newEmail@mail.com");
    }

    @Test
    public void shouldUpdateUserEmailAndPassword() {
        //given
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", "newEmail@mail.com");
        userData.put("password", new Password(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '1', '2', '3'}));

        //when
        userRepository.updatePartially(-1L, userData);

        //then
        then(userRepository.getOne(-1L).getEmail()).isEqualTo("newEmail@mail.com");
        then(userRepository.getOne(-1L).getPassword().equals(new Password(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '1', '2', '3'}))).isTrue();
    }


}
