package com.app.cms.specification;

import com.app.cms.entity.Article;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@And({
        @Spec(path = "title.value", params = "title", spec = Like.class),
        @Spec(path = "content.value", params = "content", spec = Like.class),
        @Spec(path = "category.id", params = "category", spec = Equal.class),
        @Spec(path = "rating.value", params = "ratingValue", spec = Equal.class),
        @Spec(path = "rating.count", params = "ratingCount", spec = Equal.class),
        @Spec(path = "user.id", params = "user", spec = Equal.class),
        @Spec(path = "creationDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
})
public class ArticleSpecificationImpl implements ArticleSpecification2 {
    private String title;

    public ArticleSpecificationImpl(String title) {
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (title == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }

        return criteriaBuilder.like(root.get("title").get("value"), this.title);
    }
}
