package com.app.cms.specification.category;

import com.app.cms.entity.Category;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "name.value", params = "name", spec = Like.class)
})
public interface CategorySpecification extends Specification<Category> {
}


