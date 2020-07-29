package com.app.cms.controller;

import com.app.cms.dto.CategoryDto;
import com.app.cms.dto.mapper.CategoryConverter;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryConverter categoryConverter, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable @Min(1) Long categoryId) {
        return categoryConverter.toDto(categoryRepository.getOne(categoryId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryConverter.toDto(categoryService.saveCategory(categoryConverter.toEntity(categoryDto)));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoryDto updateCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryConverter.toDto(categoryService.saveCategory(categoryConverter.toEntity(categoryDto)));
    }

    @DeleteMapping(value = "/{categoryId}")
    public void deleteCategory(@PathVariable @Min(1) Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

}
