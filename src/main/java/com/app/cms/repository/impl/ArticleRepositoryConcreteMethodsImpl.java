package com.app.cms.repository.impl;

import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepositoryConcreteMethods;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class ArticleRepositoryConcreteMethodsImpl implements ArticleRepositoryConcreteMethods {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void updatePartially(long articleId, Article article) {
        new QueryBuilder("Article")
                .addParameterIfNotNull("content", article.getContent())
                .addParameterIfNotNull("title", article.getTitle())
                .addParameterIfNotNull("rating_value", article.getRating().getRatingValue())
                .addParameterIfNotNull("rating_count", article.getRating().getRatingCount())
                .whereId(articleId)
                .runQuery(entityManager);
    }

}

class QueryBuilder {
    private StringBuilder queryText = new StringBuilder();
    private Map<String, Object> params = new HashMap<>();

     QueryBuilder(String tableName) {
         queryText.append("update ").append(tableName).append(" set");
    }

    public QueryBuilder addParameterIfNotNull(String columnName, Object value) {
         if(value != null)
             addParameter(columnName, value);

         return this;
    }

    public QueryBuilder addParameter(String columnName, Object value) {
         if(params.size() > 0)
             queryText.append(",");

        queryText.append(" columnName= :").append(columnName);
        params.put(columnName, value);

        return this;
    }

    public QueryBuilder whereId(Long id) {
        queryText.append(" where id= :id");
        params.put("id", id);

        return this;
    }

    public Query runQuery(EntityManager entityManager) {
        return entityManager.createQuery(queryText.toString());
    }



}
