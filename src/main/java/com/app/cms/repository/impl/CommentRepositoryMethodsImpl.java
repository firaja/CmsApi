package com.app.cms.repository.impl;

import com.app.cms.repository.CommentRepositoryMethods;
import com.app.cms.repository.utils.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Repository
public class CommentRepositoryMethodsImpl implements CommentRepositoryMethods {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void updatePartially(long id, Map<String, Object> changedValues) {
        new QueryBuilder("Comment").addParameters(changedValues).whereId(id).runQuery(entityManager);
    }
}
