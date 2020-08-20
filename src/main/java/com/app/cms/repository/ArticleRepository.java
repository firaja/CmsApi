package com.app.cms.repository;

import com.app.cms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryConcreteMethods {
    boolean existsByCategoryId(Long categoryId);

    boolean existsByUserId(Long userId);
}
