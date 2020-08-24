package com.app.cms.repository.impl;

import com.app.cms.repository.ArticleRepositoryMethods;
import com.app.cms.repository.utils.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Repository
public class ArticleRepositoryMethodsImpl implements ArticleRepositoryMethods {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void updatePartially(long articleId, Map<String, Object> changedValues) {
        new QueryBuilder("Article").addParameters(changedValues).whereId(articleId).runQuery(entityManager);
    }

}

