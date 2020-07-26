package com.app.cms.dto.mapper;

import com.app.cms.dto.ArticleDto;
import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import com.app.cms.repository.ArticleRepository;
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
public class ArticleMapperTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ArticleMapper articleMapper;

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
        given(categoryRepository.getOne(-20L)).willReturn(Category.builder().id(categoryId).build());
        given(userRepository.getOne(-30L)).willReturn(User.builder().id(userId).build());

        //when
        var article =  articleMapper.convertToEntity(articleDto);

        //then
        then(article).isNotNull();
        then(article.getId()).isNotNull();
        then(article.getTitle()).isEqualTo(title);
        then(article.getContent()).isEqualTo(content);
        then(article.getCreationDate()).isEqualTo(creationDate);
        then(article.getRate()).isEqualTo(rate);
        then(article.getUser()).isNotNull();
        then(article.getUser().getId()).isNotNull();
        then(article.getCategory()).isNotNull();
        then(article.getCategory().getId()).isNotNull();
    }

    @Test
    public void shouldConvertEntityToDto() {
        //given
        var article = Article.builder()
                .id(-1L)
                .title("title test")
                .content("content test")
                .rate(33)
                .user(User.builder().id(-5L).build())
                .category(Category.builder().id(-10L).build())
                .creationDate(new Date())
                .build();

        //when
        var articleDto = articleMapper.convertToDto(article);

        //then
        then(articleDto).isNotNull();
    }
}
