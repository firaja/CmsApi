package com.app.cms.dto.converter;

import com.app.cms.dto.UserDto;
import com.app.cms.entity.User;
import com.app.cms.entity.valueobjects.user.Email;
import com.app.cms.entity.valueobjects.user.Login;
import com.app.cms.entity.valueobjects.user.Password;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserConverter implements ObjectConverter<User, UserDto> {

    private final ModelMapper modelMapper;

    private final com.fasterxml.jackson.databind.ObjectMapper jacksonModelMapper;

    public UserConverter(ModelMapper modelMapper, ObjectMapper jacksonModelMapper) {
        this.modelMapper = modelMapper;
        this.jacksonModelMapper = jacksonModelMapper;
    }

    @Override
    public UserDto toDto(User user) {
        user.setPassword(null);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setLogin(user.getLogin().getValue());
        userDto.setEmail(user.getEmail().getValue());

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setLogin(new Login(userDto.getLogin()));
        user.setPassword(new Password(userDto.getPassword(), userDto.getPasswordConfirm()));
        user.setEmail(new Email(userDto.getEmail()));

        return user;
    }

    public Map<String, Object> toMap(UserDto userDto) {
        return jacksonModelMapper.convertValue(userDto, Map.class);
    }

    public User toEntity(Map<String, Object> objectAsMap) {
        return jacksonModelMapper.convertValue(objectAsMap, User.class);
    }


}
