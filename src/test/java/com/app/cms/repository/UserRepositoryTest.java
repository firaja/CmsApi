package com.app.cms.repository;

import com.app.cms.entity.User;
import com.app.cms.entity.valueobjects.user.Email;
import com.app.cms.entity.valueobjects.user.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void shouldUpdateUserEmail() {
        //given
        var user = User.builder().email(Email.of("newEmail@mail.com")).build();

        //when
        userRepository.updatePartially(-1L, user);

        //then
        then(userRepository.getOne(-1L).getEmail().getValue()).isEqualTo("newEmail@mail.com");
    }

    @Test
    public void shouldUpdateUserEmailAndPassword() {
        //given
        var user = User.builder().email(Email.of("newEmail@mail.com")).password(Password.of(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '1', '2', '3'})).build();

        //when
        userRepository.updatePartially(-1L, user);

        //then
        then(userRepository.getOne(-1L).getEmail().getValue()).isEqualTo("newEmail@mail.com");
        then(userRepository.getOne(-1L).getPassword().getValue()).isNotEmpty();
    }


}
