package com.app.cms.service;


import com.app.cms.entity.User;
import com.app.cms.repository.UserRepository;
import com.app.cms.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUser() {
        //given
        var user = User.builder().id(-1L).login("login").email("mail@mail.com")
                .password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).passwordConfirm(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'}).build();

        //when
        var savedUser = userService.saveUser(user);

        //then
        verify(userRepository, times(1)).save(any());

    }

    @Test
    public void shouldDeleteUser() {
        //given
        var userId = -1L;

        //when
        userService.deleteUser(userId);

        //then
        verify(userRepository, times(1)).deleteById(any());

    }
}
