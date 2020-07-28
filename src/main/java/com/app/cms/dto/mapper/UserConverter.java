package com.app.cms.dto.mapper;

import com.app.cms.dto.UserDto;
import com.app.cms.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements ObjectConverter<User, UserDto> {

    private final ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto toDto(User user) {
        // nie wysylac hasla
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        return user;
    }
}
