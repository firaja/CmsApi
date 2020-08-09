package com.app.cms.repository.impl;

import com.app.cms.entity.User;
import com.app.cms.repository.UserRepositoryConcreteMethods;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Map;

@Repository
public class UserRepositoryConcreteMethodsImpl implements UserRepositoryConcreteMethods {

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
