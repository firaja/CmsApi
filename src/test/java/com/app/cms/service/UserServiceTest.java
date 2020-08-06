package com.app.cms.service;


import com.app.cms.dto.converter.UserConverter;
import com.app.cms.entity.User;
import com.app.cms.repository.UserRepository;
import com.app.cms.validator.PasswordValidator;
import com.app.cms.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserValidator userValidator;

    @Mock
    private PasswordValidator passwordValidator;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUser() {
        //given
        var user = User.builder().id(-1L).login("login").email("mail@mail.com")
                .password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).build();

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

    @Test
    public void shouldUpdateUserPartially() {
        //given
        var user = User.builder().id(-1L).email("mail@mail.com").build();

        given(userConverter.toEntity(any(Map.class))).willReturn(user);
        Map<String, Object> userValues = new HashMap<>();

        //when
        userService.saveUserPartially(-1L, userValues);

        //then
        verify(userRepository, times(1)).updatePartially(any(Long.class), any(Map.class));

    }


}
