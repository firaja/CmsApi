package com.app.cms.repository;

import com.app.cms.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article>, ArticleRepositoryMethods {
    boolean existsByCategoryId(Long categoryId);

    boolean existsByUserId(Long userId);
}
