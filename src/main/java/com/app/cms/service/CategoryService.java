package com.app.cms.service;

import com.app.cms.entity.Category;
import com.app.cms.error.advice.NameAlreadyExistsException;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.validator.CategoryValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;

    public CategoryService(CategoryRepository categoryRepository, CategoryValidator categoryValidator) {
        this.categoryRepository = categoryRepository;
        this.categoryValidator = categoryValidator;
    }

    public Category createCategory(Category category) {
        categoryValidator.validate(category);

        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        categoryValidator.validate(category);
        // czy nie ma duplikacji nazwy
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        // czy nie zawiera artykulow
        categoryRepository.delete(category);
    }
}
