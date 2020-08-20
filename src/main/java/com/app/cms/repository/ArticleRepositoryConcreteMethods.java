package com.app.cms.repository;

import com.app.cms.entity.Article;

public interface ArticleRepositoryConcreteMethods {
    void updatePartially(long articleId, Article article);
}
