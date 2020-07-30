package com.app.cms.repository;

import com.app.cms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    //  boolean existsByCategory(Category category);
    boolean existsByCategoryId(Long categoryId);

    //    boolean existsByUser(User user);
    boolean existsByUserId(Long userId);
}
