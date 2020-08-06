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
    public void updatePartially(long userId, Map<String, Object> fields) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> criteria = builder.createCriteriaUpdate(User.class);
        Root<User> root = criteria.from(User.class);
        fields.keySet().stream().forEach(key -> criteria.set(key, fields.get(key)));
        criteria.where(builder.equal(root.get("id"), userId));

        entityManager.createQuery(criteria).executeUpdate();
    }

}
