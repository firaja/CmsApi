package com.app.cms.service;

import com.app.cms.dto.converter.UserConverter;
import com.app.cms.entity.User;
import com.app.cms.repository.UserRepository;
import com.app.cms.validator.PasswordValidator;
import com.app.cms.validator.UserValidator;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.SecureString;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final UserConverter userConverter;

    public UserService(UserValidator userValidator, UserRepository userRepository, PasswordValidator passwordValidator, UserConverter userConverter) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.userConverter = userConverter;
    }

    public User saveUser(User user) {
        userValidator.validateOnSave(user);
        passwordValidator.validateOnSave(user.getPassword());

        user.setPassword(hashPass(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    public void saveUserPartially(Long userId, Map<String, Object> fields) {
        var user = userConverter.toEntity(fields);
        user.setId(userId);
        userValidator.validateOnSave(user);

        if(user.getPassword() != null)
            user.setPassword(hashPass(user.getPassword()));

        userRepository.updatePartially(userId, fields);
    }

    private char[] hashPass(char[] password) {
        SecureString securedPassword = new SecureString(password);
        Hash hash = Password.hash(securedPassword).withBCrypt();

        return hash.getResult().toCharArray();
    }

    public void deleteUser(Long userId) {
        userValidator.validateOnDelete(userId);
        userRepository.deleteById(userId);
    }

}
