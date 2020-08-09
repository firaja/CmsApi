package com.app.cms.repository;

import com.app.cms.entity.User;


public interface UserRepositoryConcreteMethods {
    void updatePartially(long userId, User user);
}
