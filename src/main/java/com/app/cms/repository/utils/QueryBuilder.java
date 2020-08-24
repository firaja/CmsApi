package com.app.cms.repository.utils;

import com.app.cms.error.type.QueryParamsNotSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
    private final StringBuilder queryText = new StringBuilder();
    private final Map<String, Object> params = new HashMap<>();

    public QueryBuilder(String tableName) {
        queryText.append("update ").append(tableName).append(" set ");
    }

    public QueryBuilder addParameterIfIsSet(String columnName, Object value) {
        if (value != null)
            addParameter(columnName, value);

        return this;
    }

    public QueryBuilder addParameters(Map<String, Object> changedValues) {
        for (Map.Entry<String, Object> changedValue : changedValues.entrySet()) {
            addParameter(changedValue.getKey(), changedValue.getValue());
        }

        return this;
    }

    public QueryBuilder addParameter(String columnName, Object value) {
        if (params.size() > 0)
            queryText.append(",");

        queryText.append(splitForColumnNaming(columnName)).append("= :").append(columnName);
        params.put(columnName, value);

        return this;
    }

    public QueryBuilder whereId(Long id) {
        queryText.append(" where id= :id");
        params.put("id", id);

        return this;
    }

    public void runQuery(EntityManager entityManager) {
        Query query = entityManager.createNativeQuery(queryText.toString());
        rewriteParamsToQuery(query);
        query.executeUpdate();
    }

    private void rewriteParamsToQuery(Query query) {
        checkParamsAreSet();

        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
    }

    private void checkParamsAreSet() {
        if (params.size() < 2)
            throw new QueryParamsNotSet("Query parameters must be set");
    }

    private String splitForColumnNaming(String key) {
        if (key.chars().anyMatch(Character::isUpperCase)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append('_');
                }
                sb.append(c);
            }
            return sb.toString();
        } else
            return key;
    }
}
