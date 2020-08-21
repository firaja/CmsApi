package com.app.cms.service;

import com.app.cms.dto.converter.UserConverter;
import com.app.cms.entity.User;
import com.app.cms.repository.UserRepository;

import com.app.cms.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserValidator userValidator, UserRepository userRepository, UserConverter userConverter) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public User save(User user) {
        userValidator.validateOnSave(user);
        userRepository.save(user);

        return user;
    }

    public void saveUserPartially(Long userId, Map<String, Object> fields) {
        var user = userConverter.toEntity(fields);
        user.setId(userId);
        userValidator.validateOnSave(user);

        userRepository.updatePartially(userId, user);
    }

    public void delete(Long userId) {
        userValidator.validateOnDelete(userId);
        userRepository.deleteById(userId);
    }
}
