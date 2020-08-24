package com.app.cms.validator;

import com.app.cms.entity.Category;
import com.app.cms.error.type.NameIsInUseException;
import com.app.cms.error.type.ObjectHaveReferencedObjects;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator implements ValidatorOnSave<Category>, ValidatorOnDelete {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    public CategoryValidator(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void validateOnSave(Category category) {
        if (category.getId() == null) {
            validationOnCreation(category);
        } else {
            validationOnUpdate(category);
        }
    }

    @Override
    public void validateOnDelete(Long categoryId) {
        if (articleRepository.existsByCategoryId(categoryId)) {
            throw new ObjectHaveReferencedObjects("Category contains articles");
        }
    }

    private void validationOnUpdate(Category category) {
        if (categoryRepository.existsByNameAndIdNot(category.getName().getValue(), category.getId())) {
            throw new NameIsInUseException("Category name is already in use");
        }
    }

    private void validationOnCreation(Category category) {
        if (categoryRepository.existsByName(category.getName().getValue())) {
            throw new NameIsInUseException("Category name is already in use");
        }
    }
}
