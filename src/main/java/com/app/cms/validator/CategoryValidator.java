package com.app.cms.validator;

import com.app.cms.entity.Category;
import com.app.cms.error.advice.NameAlreadyExistsException;
import com.app.cms.repository.CategoryRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator implements Validator<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void validate(Category category) {
        if(category.getId() == null) {
            validationOnCreation(category);
        }
        else {
            validationOnUpdate(category);
        }
    }

    private void validationOnUpdate(Category category) {
        if(categoryRepository.existsByNameAndIdNot(category.getName(), category.getId())) {

        }
    }

    private void validationOnCreation(Category category) {
      //  if(categoryRepository.existsByName(category.getName())) {
            if(true) {
                JSONObject entity = new JSONObject();
                entity.put("message","Validation failed");

                JSONObject entity2 = new JSONObject();
                entity2.put("message","Category name is already in use");
                entity2.put("field","name");

                JSONArray errors = new JSONArray();
                errors.put(entity2);

                entity.put("errors",errors);

                throw new NameAlreadyExistsException(entity.toString());


        }
    }

}
