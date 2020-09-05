package com.app.cms.specification.article;

import com.app.cms.entity.Article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class ArticleWithCategory implements ArticleSpecification {
    private Long categoryId;

    public ArticleWithCategory(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Objects.requireNonNull(categoryId);

        return criteriaBuilder.equal(root.get("category").get("id"), this.categoryId);
    }
}
