package com.app.cms.service;

import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    @Test
    public void shouldCreateArticle() {
        //given
        var article = Article.builder().title("test title").content("test content").build();

        //when
       // Article articleService.create(article);


        //then

    }
}
