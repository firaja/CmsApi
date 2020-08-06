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
            var validationError = new ValidationError()
                    .appendDetail("name", "Category contains articles");

            throw new ObjectHaveReferencedObjects(validationError.toString());
        }
    }

    private void validationOnUpdate(Category category) {
        if (categoryRepository.existsByNameAndIdNot(category.getName(), category.getId())) {
            throwNameIsInUseException();
        }
    }

    private void validationOnCreation(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throwNameIsInUseException();
        }
    }

    private void throwNameIsInUseException() {
        var validationError = new ValidationError()
                .appendDetail("name", "Category name is already in use");

        throw new NameIsInUseException(validationError.toString());
    }

}
