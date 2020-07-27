package com.app.cms.controller;

import com.app.cms.dto.CategoryDto;
import com.app.cms.dto.mapper.CategoryConverter;
import com.app.cms.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryController(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @GetMapping
    public CategoryDto getCategoryById(@PathVariable @Min(1) Long categoryId) {
        return categoryConverter.toDto(categoryRepository.getOne(categoryId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

}
