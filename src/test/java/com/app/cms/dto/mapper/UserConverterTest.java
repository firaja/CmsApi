package com.app.cms.dto.mapper;

import com.app.cms.dto.UserDto;
import com.app.cms.dto.converter.UserConverter;
import com.app.cms.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserConverterTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private UserConverter userConverter;

    @Test
    public void shouldConvertDtoToEntity() {
        //given
        UserDto userDto = UserDto.builder().id(-1L).login("login").email("mail@mail.com")
                .password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).passwordConfirm(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'}).build();

        //when
        var user = userConverter.toEntity(userDto);

        //then
        then(user.getId()).isEqualTo(-1L);
        then(user.getLogin()).isEqualTo("login");
        then(user.getEmail()).isEqualTo("mail@mail.com");
        then(user.getPassword()).isEqualTo(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'});
        then(user.getPasswordConfirm()).isEqualTo(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'});
    }

    @Test
    public void shouldConvertEntityToDto() {
        //given
        var user = User.builder().id(-1L).login("login").email("mail@mail.com")
                .password(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'}).passwordConfirm(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd', '2'}).build();

        //when
        var userDto = userConverter.toDto(user);

        //then
        then(userDto.getId()).isEqualTo(-1L);
        then(userDto.getLogin()).isEqualTo("login");
        then(userDto.getEmail()).isEqualTo("mail@mail.com");
        then(userDto.getPassword()).isNull();
        then(userDto.getPasswordConfirm()).isNull();
    }

}
