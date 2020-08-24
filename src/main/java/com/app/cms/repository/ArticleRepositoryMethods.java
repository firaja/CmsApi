package com.app.cms.repository;

import java.util.Map;

public interface ArticleRepositoryMethods {
    void updatePartially(long articleId, Map<String, Object> changedValues);
}
