package com.app.cms.service;

import com.app.cms.entity.User;
import com.app.cms.repository.UserRepository;
import com.app.cms.validator.UserValidator;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.SecureString;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;

    public UserService(UserValidator userValidator, UserRepository userRepository) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        userValidator.validate(user);


        SecureString password = new SecureString(user.getPassword());
        Hash hash = Password.hash(password).withBCrypt();

        userRepository.save(user);

        // https://github.com/Password4j/password4j
        // Password.check()

        return user;
    }

    public void deleteUser(Long userId) {
        userValidator.validateOnDelete(userId);
        // jesli zawiera arytkuly to nie kasowac

        userRepository.deleteById(userId);
    }

}
