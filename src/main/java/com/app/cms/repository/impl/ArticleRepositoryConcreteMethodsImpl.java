package com.app.cms.repository.impl;

import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepositoryConcreteMethods;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ArticleRepositoryConcreteMethodsImpl implements ArticleRepositoryConcreteMethods {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void updatePartially(long articleId, Article article) {

        var customCriteriaBuilder = new CustomCriteriaBuilder(Article.class, entityManager);


        customCriteriaBuilder.setIfNotNull("content", article.getContent());
        customCriteriaBuilder.setIfNotNull("title", article.getTitle());
        customCriteriaBuilder.setIfNotNull("category", article.getCategory());
        customCriteriaBuilder.setIfNotNull("user", article.getUser());

        customCriteriaBuilder.getCriteria().set(customCriteriaBuilder.getCriteria().getRoot().get("rating"), 1F);

        customCriteriaBuilder.where(articleId);
        customCriteriaBuilder.execute();
    }
}
