package com.app.cms.service;

import com.app.cms.dto.converter.UserConverter;
import com.app.cms.entity.User;
import com.app.cms.repository.UserRepository;
import com.app.cms.validator.UserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public void saveUserPartially(Long userId, Map<String, Object> changedValues) {
        var user = userConverter.toEntity(changedValues);
        user.setId(userId);

        userValidator.validateOnSave(user);
        userRepository.updatePartially(userId, changedValues);
    }

    public void delete(Long userId) {
        userValidator.validateOnDelete(userId);
        userRepository.deleteById(userId);
    }


    public Page<User> get(Specification<User> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }
}
