package com.app.cms.dto.converter;

import com.app.cms.dto.UserDto;
import com.app.cms.entity.User;
import com.app.cms.error.type.PasswordsAreNotSameException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User toEntity(UserDto userDto) {
        if (!Arrays.equals(userDto.getPassword(), userDto.getPasswordConfirm()))
            throw new PasswordsAreNotSameException("Passwords are different");

        return modelMapper.map(userDto, User.class);
    }

    public Map<String, Object> toMap(UserDto userDto) {
        return jacksonModelMapper.convertValue(userDto, Map.class);
    }

    public User toEntity(Map<String, Object> objectAsMap) {
        return jacksonModelMapper.convertValue(objectAsMap, User.class);
    }


}
