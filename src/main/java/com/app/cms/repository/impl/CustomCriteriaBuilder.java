package com.app.cms.repository.impl;

import com.app.cms.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

public class CustomCriteriaBuilder {
    private CriteriaBuilder builder;
    private Root<User> root;
    private CriteriaUpdate<User> criteria;
    private EntityManager entityManager;

    public CustomCriteriaBuilder(Class clazz, EntityManager entityManager) {
        builder = entityManager.getCriteriaBuilder();
        criteria = builder.createCriteriaUpdate(clazz);
        root = criteria.from(clazz);
        this.entityManager = entityManager;
    }

    public void setIfNotNull(String path, Object value) {
        if(value != null)
            criteria.set(path, value);
    }

    public void where(Long id) {
        criteria.where(builder.equal(root.get("id"), id));
    }

    public void execute() {
        entityManager.createQuery(criteria).executeUpdate();
    }

}
