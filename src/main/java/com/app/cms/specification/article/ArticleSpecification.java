package com.app.cms.specification.article;

import com.app.cms.entity.Article;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "title.value", params = "title", spec = Like.class),
        @Spec(path = "content.value", params = "content", spec = Like.class),
        @Spec(path = "category.id", params = "category", spec = Equal.class),
        @Spec(path = "rating.value", params = "ratingValue", spec = Equal.class),
        @Spec(path = "rating.count", params = "ratingCount", spec = Equal.class),
        @Spec(path = "user.id", params = "user", spec = Equal.class),
        @Spec(path = "creationDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
})
public interface ArticleSpecification extends Specification<Article> {
}


