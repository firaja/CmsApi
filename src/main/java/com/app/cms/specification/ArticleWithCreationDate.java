package com.app.cms.specification;

import com.app.cms.entity.Article;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class ArticleWithCreationDate implements ArticleSpecification {
    private LocalDate creationFrom;
    private LocalDate creationTo;

    public ArticleWithCreationDate(LocalDate creationFrom, LocalDate creationTo) {
        this.creationFrom = creationFrom;
        this.creationTo = creationTo;
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.between(root.get("creationDate"), this.creationFrom, this.creationTo);
    }
}
