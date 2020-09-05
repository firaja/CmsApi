package com.app.cms.repository;

import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, long id);


}
