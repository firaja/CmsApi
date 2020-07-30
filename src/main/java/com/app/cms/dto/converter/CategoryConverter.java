package com.app.cms.dto.converter;

import com.app.cms.dto.CategoryDto;
import com.app.cms.entity.Category;
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
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
