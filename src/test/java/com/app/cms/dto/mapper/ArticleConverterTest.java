package com.app.cms.dto.mapper;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import com.app.cms.entity.values.article.Content;
import com.app.cms.entity.values.article.CreationDate;
import com.app.cms.entity.values.article.Rating;
import com.app.cms.entity.values.article.Title;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleConverterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ArticleConverter articleConverter;

    @Test
    public void shouldConvertDtoToEntity() {
        //given
        String title = "title test";
        String content = "content test";
        Date creationDate = new Date();
        long categoryId = -20L;
        long userId = -30L;
        int rate = 10;

        var articleDto = ArticleDto.builder()
                .id(-1L)
                .title(title)
                .content(content)
                .categoryId(categoryId)
                .userId(userId)
                .creationDate(creationDate)
                .rate(rate)
                .build();

        given(categoryRepository.getOne(categoryId)).willReturn(Category.builder().id(categoryId).build());
        given(userRepository.getOne(userId)).willReturn(User.builder().id(userId).build());

        //when
        var article =  articleConverter.toEntity(articleDto);

        //then
        then(article).isNotNull();
        then(article.getId()).isNotNull();
        then(article.getTitle()).isEqualTo(title);
        then(article.getContent()).isEqualTo(content);
        then(article.getCreationDate()).isEqualTo(creationDate);
        then(article.getRating()).isEqualTo(rate);
        then(article.getUser()).isNotNull();
        then(article.getUser().getId()).isNotNull();
        then(article.getCategory()).isNotNull();
        then(article.getCategory().getId()).isNotNull();
    }

    @Test
    public void shouldConvertEntityToDto() {
        //given
        String title = "title test";
        String content = "content test";
        long articleId = -1L;
        long userId = -5L;
        long categoryId = -10L;
        int rating = 33;

        var article = Article.builder()
                .id(articleId)
                .title(new Title(title))
                .content(new Content(content))
                .rating(new Rating(rating))
                .user(User.builder().id(userId).build())
                .category(Category.builder().id(categoryId).build())
                .creationDate(new CreationDate(new Date()))
                .build();

        //when
        var articleDto = articleConverter.toDto(article);

        //then
        then(articleDto.getId()).isEqualTo(articleId);
        then(articleDto.getTitle()).isEqualTo(title);
        then(articleDto.getContent()).isEqualTo(content);
        then(articleDto.getCategoryId()).isEqualTo(categoryId);
        then(articleDto.getUserId()).isEqualTo(userId);
        then(articleDto.getRate()).isEqualTo(rating);
        then(articleDto.getCreationDate()).isNotNull();
    }
}
