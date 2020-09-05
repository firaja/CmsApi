package com.app.cms.specification.article;

import com.app.cms.entity.Article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;


public class ArticleWithTitle implements ArticleSpecification {
    private String title;

    public ArticleWithTitle(String title) {
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Objects.requireNonNull(title);

        return criteriaBuilder.like(root.get("title").get("value"), this.title);
    }
}
