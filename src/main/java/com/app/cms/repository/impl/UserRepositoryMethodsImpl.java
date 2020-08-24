package com.app.cms.repository.impl;

import com.app.cms.entity.User;
import com.app.cms.repository.UserRepositoryMethods;
import com.app.cms.repository.utils.CustomCriteriaBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UserRepositoryMethodsImpl implements UserRepositoryMethods {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void updatePartially(long userId, User user) {
        var customCriteriaBuilder = new CustomCriteriaBuilder(User.class, entityManager);

        customCriteriaBuilder.setIfNotNull("login", user.getLogin());
        customCriteriaBuilder.setIfNotNull("email", user.getEmail());
        customCriteriaBuilder.setIfNotNull("password", user.getPassword());

        customCriteriaBuilder.execute();
    }
}