package com.app.cms.repository;

import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByCategory(Category category);
}
