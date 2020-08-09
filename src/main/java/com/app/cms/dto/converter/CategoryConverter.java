package com.app.cms.dto.converter;

import com.app.cms.dto.CategoryDto;
import com.app.cms.entity.Category;
import com.app.cms.entity.values.category.Name;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements ObjectConverter<Category, CategoryDto> {

    private final ModelMapper modelMapper;

    public CategoryConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto toDto(Category category) {
        var categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setName(category.getName().getValue());

        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        var category = modelMapper.map(categoryDto, Category.class);
        category.setName(new Name(categoryDto.getName()));

        return category;
    }
}
