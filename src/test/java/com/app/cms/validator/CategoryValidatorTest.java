package com.app.cms.validator;

import com.app.cms.entity.Category;
import com.app.cms.entity.valueobjects.category.Name;
import com.app.cms.error.type.NameIsInUseException;
import com.app.cms.error.type.ObjectHaveReferencedObjects;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryValidatorTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private CategoryValidator categoryValidator;

    @Test
    public void shouldThrowError_WhenCreateNewCategory_CategoryWithSameNameExists() {
        //given
        var category = Category.builder().name(Name.of("name")).build();
        given(categoryRepository.existsByName(any(String.class))).willReturn(true);

        //when, then
        assertThatThrownBy(() -> {
            categoryValidator.validateOnSave(category);
        }).isInstanceOf(NameIsInUseException.class).hasMessageContaining("Category name is already in use");
    }

    @Test
    public void shouldThrowError_WhenUpdateCategory_CategoryWithSameNameExists() {
        //given
        var category = Category.builder().id(-1L).name(Name.of("name")).build();
        given(categoryRepository.existsByNameAndIdNot(any(String.class), any(Long.class))).willReturn(true);

        //when, then
        assertThatThrownBy(() -> {
            categoryValidator.validateOnSave(category);
        }).isInstanceOf(NameIsInUseException.class).hasMessageContaining("Category name is already in use");
    }

    @Test
    public void shouldValidateCategoryCorrect_WhenCreateNewCategory() {
        //given
        given(articleRepository.existsByCategoryId(any(Long.class))).willReturn(false);

        //when, then
        categoryValidator.validateOnDelete(-1L);
    }

    @Test
    public void shouldValidateCategoryCorrect_WhenUpdateCategory() {
        //given
        given(categoryRepository.existsByNameAndIdNot(any(String.class), any(Long.class))).willReturn(false);

        //when, then
        categoryValidator.validateOnDelete(-1L);
    }

    @Test
    public void shouldThrowError_WhenDeleteCategory_CategoryHaveArticles() {
        //given
        given(articleRepository.existsByCategoryId(any(Long.class))).willReturn(true);

        //when, then
        assertThatThrownBy(() -> {
            categoryValidator.validateOnDelete(-1L);
        }).isInstanceOf(ObjectHaveReferencedObjects.class).hasMessageContaining("Category contains articles");
    }

    @Test
    public void shouldValidateCategoryDeleteCorrect() {
        //given
        given(articleRepository.existsByCategoryId(any(Long.class))).willReturn(false);

        //when, then
        categoryValidator.validateOnDelete(-1L);
    }
}
