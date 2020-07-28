package com.app.cms.service;

import com.app.cms.entity.User;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.SecureString;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User createUser(User user) {
        SecureString password = new SecureString(user.getPassword());
        Hash hash = Password.hash(password).withBCrypt();

        // https://github.com/Password4j/password4j
       // Password.check()

        return null;
    }
}
