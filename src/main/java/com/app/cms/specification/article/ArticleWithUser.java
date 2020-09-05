package com.app.cms.specification.article;

import com.app.cms.entity.Article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class ArticleWithUser implements ArticleSpecification {
    private Long userId;

    public ArticleWithUser(Long userId) {
        this.userId = userId;
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Objects.requireNonNull(userId);

        return criteriaBuilder.equal(root.get("user").get("id"), this.userId);
    }
}
