package com.app.cms.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

public class CustomCriteriaBuilder {
    private CriteriaBuilder builder;
    private Root<Object> root;
    private CriteriaUpdate<Object> criteria;
    private EntityManager entityManager;

    public CustomCriteriaBuilder(Class clazz, EntityManager entityManager) {
        builder = entityManager.getCriteriaBuilder();
        criteria = builder.createCriteriaUpdate(clazz);
        root = criteria.from(clazz);
        this.entityManager = entityManager;
    }

    public void setIfNotNull(String path, Object value) {
        if (value != null)
            criteria.set(path, value);
    }

    public Root<Object> getRoot() {
        return root;
    }

    public CriteriaUpdate<Object> getCriteria() {
        return criteria;
    }

    public void set(String fieldName, Object value) {
        criteria.set(fieldName, value);
    }

    public void where(Long id) {
        criteria.where(builder.equal(root.get("id"), id));
    }

    public void execute() {
        entityManager.createQuery(criteria).executeUpdate();
    }

    public void execute2() {
        Query query = entityManager.createQuery(criteria);
    }

}
