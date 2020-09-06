package com.app.cms.dto.mapper;

import com.app.cms.dto.UserDto;
import com.app.cms.dto.converter.UserConverter;
import com.app.cms.entity.User;
import com.app.cms.error.type.PasswordsAreNotSameException;
import com.app.cms.valueobject.user.Email;
import com.app.cms.valueobject.user.Login;
import com.app.cms.valueobject.user.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserConverterTest {

    @Spy
    private ModelMapper modelMapper;

    @Spy
    private com.fasterxml.jackson.databind.ObjectMapper jacksonModelMapper;

    @InjectMocks
    private UserConverter userConverter;

    @Test
    public void shouldConvertDtoToEntity() {
        //given
        UserDto userDto = UserDto.builder().id(-1L).login("login").email("mail@mail.com")
                .password(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd'}).passwordConfirm(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd'}).build();

        //when
        var user = userConverter.toEntity(userDto);

        //then
        then(user.getId()).isEqualTo(-1L);
        then(user.getLogin().getValue()).isEqualTo("login");
        then(user.getEmail().getValue()).isEqualTo("mail@mail.com");
        then(user.getPassword()).isNotNull();
    }

    @Test
    public void shouldThrowPasswordAreNotSameException() {
        //given when then
        assertThatThrownBy(() -> {
            userConverter.toEntity(UserDto.builder().id(-1L).login("login").email("mail@mail.com")
                    .password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).passwordConfirm(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'}).build());
        })
                .isInstanceOf(PasswordsAreNotSameException.class);
    }

    @Test
    public void shouldConvertEntityToDto() {
        //given
        var user = User.builder().id(-1L).login(Login.of("login")).email(Email.of("mail@mail.com"))
                .password(Password.of(new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd'}, new char[]{'P', 'a', 's', 's', 'w', 'o', 'r', 'd'})).build();

        //when
        var userDto = userConverter.toDto(user);

        //then
        then(userDto.getId()).isEqualTo(-1L);
        then(userDto.getLogin()).isEqualTo(Login.of("login").getValue());
        then(userDto.getEmail()).isEqualTo(Email.of("mail@mail.com").getValue());
        then(userDto.getPassword()).isNull();
        then(userDto.getPasswordConfirm()).isNull();
    }

    @Test
    public void shouldConvertEntityToMap() {
        //given
        UserDto userDto = UserDto.builder().id(-1L).login("login123").email("mail@mail.com")
                .password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).passwordConfirm(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).build();

        //when
        Map<String, Object> userAsMap = userConverter.toMap(userDto);

        //then
        then(userAsMap.get("id")).isEqualTo(-1L);
        then(userAsMap.get("login")).isEqualTo("login123");
        then(userAsMap.get("email")).isEqualTo("mail@mail.com");
        then(userAsMap.get("password")).isNotNull();
    }

}
