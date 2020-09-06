package com.app.cms.service;

import com.app.cms.entity.Category;
import com.app.cms.error.type.NameIsInUseException;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.validator.CategoryValidator;
import com.app.cms.valueobject.category.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
        final var categoryToSave = Category.builder().name(Name.of("category 1")).build();
        given(categoryRepository.save(any(Category.class))).willReturn(categoryToSave.toBuilder().id(-1L).build());

        //when
        var savedCategory = categoryService.save(categoryToSave);

        //then
        then(savedCategory.getId()).isEqualTo(-1L);
        then(savedCategory.getName().getValue()).isEqualTo("category 1");
    }

    @Test
    public void shouldUpdateCategory() {
        //given
        final var categoryToSave = Category.builder().id(-1L).name(Name.of("category 1")).build();
        given(categoryRepository.save(any(Category.class))).willReturn(categoryToSave.toBuilder().build());

        //when
        var savedCategory = categoryService.save(categoryToSave);

        //then
        then(savedCategory.getId()).isEqualTo(-1L);
        then(savedCategory.getName().getValue()).isEqualTo("category 1");
    }

    @Test
    public void shouldThrowError_WhenUpdateCategory_ValidationFail() {
        //given
        final var categoryToSave = Category.builder().id(-1L).name(Name.of("category 1")).build();
        given(categoryRepository.save(any(Category.class))).willReturn(categoryToSave.toBuilder().build());
        doThrow(new NameIsInUseException("Name in use")).when(categoryValidator).validateOnSave(categoryToSave);

        //when, then
        assertThatThrownBy(() -> {
            categoryValidator.validateOnSave(categoryToSave);
        }).isInstanceOf(NameIsInUseException.class).hasMessageContaining("Name in use");
    }

    @Test
    public void shouldDeleteCategory() {
        //when
        categoryService.delete(-1L);

        //then
        verify(categoryRepository, times(1)).deleteById(any(Long.class));
    }
}
