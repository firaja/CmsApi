package com.app.cms.repository;

import com.app.cms.entity.User;


public interface UserRepositoryMethods {
    void updatePartially(long userId, User user);
}
