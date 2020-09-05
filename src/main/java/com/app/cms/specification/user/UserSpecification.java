package com.app.cms.specification.user;

import com.app.cms.entity.User;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "login.value", params = "login", spec = Like.class),
        @Spec(path = "email.value", params = "email", spec = Like.class)
})
public interface UserSpecification extends Specification<User> {
}
