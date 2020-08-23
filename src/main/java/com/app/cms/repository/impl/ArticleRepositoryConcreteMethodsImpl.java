package com.app.cms.repository.impl;

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
    public void updatePartially(long articleId, Map<String, Object> changedValues) {
        QueryBuilder queryBuilder = new QueryBuilder("Article");

        for (Map.Entry<String, Object> changedValue : changedValues.entrySet()) {
            queryBuilder.addParameter(changedValue.getKey(), changedValue.getValue());
        }

        queryBuilder.whereId(articleId);
        queryBuilder.runQuery(entityManager);
    }


 /*   public void updatePartially(long articleId, Article article) {
        new QueryBuilder("Article")
                .addParameterIfIsSet("content", article.getContent())
                .addParameterIfIsSet("title", article.getTitle())
                .addParameterIfIsSet("rating_value", article.getRating() != null ? article.getRating().getRatingValue() : null) //co tuu trzeba zrobic
                .addParameterIfIsSet("rating_count", article.getRating() != null ? article.getRating().getRatingCount() : null)
                .addParameterIfIsSet("title", article.getTitle().getValue() != null ? article.getTitle().getValue() : null)
                .addParameterIfIsSet("rating_count", article.getRating() != null ? article.getRating().getRatingCount() : null)
                .whereId(articleId)
                .runQuery(entityManager);
    }*/


}

class QueryBuilder {
    private StringBuilder queryText = new StringBuilder();
    private Map<String, Object> params = new HashMap<>();

     QueryBuilder(String tableName) {
         queryText.append("update ").append(tableName).append(" set");
    }

    public QueryBuilder addParameterIfIsSet(String columnName, Object value) {
        if (value != null)
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
