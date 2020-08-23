package com.app.cms.dto.mapper;

import com.app.cms.dto.CategoryDto;
import com.app.cms.dto.converter.CategoryConverter;
import com.app.cms.entity.Category;
import com.app.cms.entity.valueobjects.category.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryConverterTest {

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryConverter categoryConverter;

    @Test
    public void shouldConvertDtoToEntity() {
        //given
        var category = Category.builder().id(-1L).name(Name.of("category")).build();

        //when
        var categoryDto = categoryConverter.toDto(category);

        //then
        assertThat(categoryDto.getId()).isEqualTo(-1L);
        assertThat(categoryDto.getName()).isEqualTo(Name.of("category").getValue());
    }

    @Test
    public void shouldConvertEntityToDto() {
        //given
        var categoryDto = CategoryDto.builder().id(-1L).name("category").build();

        //when
        var category = categoryConverter.toEntity(categoryDto);

        //then
        assertThat(category.getId()).isEqualTo(-1L);
        assertThat(category.getName().getValue()).isEqualTo("category");
    }
}
