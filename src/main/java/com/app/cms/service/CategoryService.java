package com.app.cms.service;

import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.validator.CategoryValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;

    public CategoryService(CategoryRepository categoryRepository, CategoryValidator categoryValidator) {
        this.categoryRepository = categoryRepository;
        this.categoryValidator = categoryValidator;
    }

    public Category save(Category category) {
        categoryValidator.validateOnSave(category);

        return categoryRepository.save(category);
    }

    public void delete(Long categoryId) {
        categoryValidator.validateOnDelete(categoryId);

        categoryRepository.deleteById(categoryId);
    }


    public Page<Category> get(Specification<Category> spec, Pageable pageable) {
        return categoryRepository.findAll(spec, pageable);
    }
}
