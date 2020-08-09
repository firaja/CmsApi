package com.app.cms.controller;

import com.app.cms.dto.CategoryDto;
import com.app.cms.dto.converter.CategoryConverter;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        return categoryConverter.toDto(categoryRepository.getOne(categoryId));
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryConverter::toDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryConverter.toDto(categoryService.saveCategory(categoryConverter.toEntity(categoryDto)));
    }

    @PutMapping
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryConverter.toDto(categoryService.saveCategory(categoryConverter.toEntity(categoryDto)));
    }

    @DeleteMapping(value = "/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

}
