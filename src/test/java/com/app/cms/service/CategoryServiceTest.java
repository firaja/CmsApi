package com.app.cms.service;

import com.app.cms.entity.Category;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.validator.CategoryValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryValidator categoryValidator;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void shouldCreateCategory() {
        //given
        final var categoryToSave = Category.builder().name("category 1").build();
        given(categoryRepository.save(any(Category.class))).willReturn(categoryToSave.toBuilder().id(-1L).build());

        //when
        var savedCategory = categoryService.saveCategory(categoryToSave);

        //then
        then(savedCategory.getId()).isEqualTo(-1L);
        then(savedCategory.getName()).isEqualTo("category 1");
    }

 /*   @Test
    public void shouldReturnErrorBecCategoryWithSameNameExists() {
        //given
        final var categoryToSave = Category.builder().name("category 1").build();
        given(categoryRepository.existsByName(any(String.class))).willReturn(true);

        //when
        var savedCategory = categoryService.createCategory(categoryToSave);

        //then
        then(savedCategory.getId()).isEqualTo(-1L);
        then(savedCategory.getName()).isEqualTo("category 1");
    }*/
}
