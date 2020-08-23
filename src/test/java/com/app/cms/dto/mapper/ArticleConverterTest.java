package com.app.cms.dto.mapper;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import com.app.cms.entity.valueobjects.article.Content;
import com.app.cms.entity.valueobjects.article.Rating;
import com.app.cms.entity.valueobjects.article.Title;
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
import java.util.HashMap;
import java.util.Map;

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
    private com.fasterxml.jackson.databind.ObjectMapper jacksonModelMapper;

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
        float rateValue = 4.3F;

        var articleDto = ArticleDto.builder()
                .id(-1L)
                .title(title)
                .content(content)
                .categoryId(categoryId)
                .userId(userId)
                .creationDate(creationDate)
                .ratingValue(rateValue)
                .ratingCount(5)
                .build();

        given(categoryRepository.getOne(categoryId)).willReturn(Category.builder().id(categoryId).build());
        given(userRepository.getOne(userId)).willReturn(User.builder().id(userId).build());

        //when
        var article = articleConverter.toEntity(articleDto);

        //then
        then(article).isNotNull();
        then(article.getId()).isNotNull();
        then(article.getTitle().getValue()).isEqualTo(title);
        then(article.getContent().getValue()).isEqualTo(content);
        then(article.getCreationDate()).isEqualTo(creationDate);
        then(article.getRating().getValue()).isEqualTo(rateValue);
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
        float ratingValue = 3.2F;
        int ratingCount = 6;

        var article = Article.builder()
                .id(articleId)
                .title(Title.of(title))
                .content(Content.of(content))
                .rating(Rating.of(ratingValue, ratingCount))
                .user(User.builder().id(userId).build())
                .category(Category.builder().id(categoryId).build())
                .creationDate(new Date())
                .build();

        //when
        var articleDto = articleConverter.toDto(article);

        //then
        then(articleDto.getId()).isEqualTo(articleId);
        then(articleDto.getTitle()).isEqualTo(title);
        then(articleDto.getContent()).isEqualTo(content);
        then(articleDto.getCategoryId()).isEqualTo(categoryId);
        then(articleDto.getUserId()).isEqualTo(userId);
        then(articleDto.getRatingValue()).isEqualTo(ratingValue);
        then(articleDto.getCreationDate()).isNotNull();
    }

    @Test
    public void shouldConvertMapToEntity() {
        //given
/*       final var objectValues = Article.builder()
                .title(Title.of("updated title1"))
                .content(Content.of("updated content1"))
                .user(User.builder().id(-2L).build())
                .category(Category.builder().id(-2L).build())
          //      .rating(new Rating(2.3F, 6))
                .build();*/

        Map<String, Object> changedValues = new HashMap<>();
        changedValues.put("title", "this is new title");
        changedValues.put("ratingCount", "3");
        changedValues.put("ratingValue", "4.5");

        //when
        Article article = articleConverter.toEntity(changedValues);
        //    articleRepository.updatePartially(-1L, articleToSave);
        //    articleRepository.updatePartially(-1L, changedValues);

        //then
        then(article.getTitle().getValue()).isEqualTo("this is new title");
      /*  then(savedArticle.getContent().getValue()).isEqualTo("updated content1");
        then(savedArticle.getUser().getId()).isEqualTo(-2L);
        then(savedArticle.getCategory().getId()).isEqualTo(-2L);
        then(savedArticle.getRating().getValue()).isEqualTo(2.3F);
        then(savedArticle.getRating().getCount()).isEqualTo(6);*/
    }


}
