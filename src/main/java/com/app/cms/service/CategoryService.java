package com.app.cms.service;

import com.app.cms.entity.Category;
import com.app.cms.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        // czy nie ma duplikacji nazwy
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        // czy nie zawiera artykulow
        categoryRepository.delete(category);
    }
}
