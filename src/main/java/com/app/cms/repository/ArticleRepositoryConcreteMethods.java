package com.app.cms.repository;

import java.util.Map;

public interface ArticleRepositoryConcreteMethods {
    void updatePartially(long articleId, Map<String, Object> changedValues);
}
