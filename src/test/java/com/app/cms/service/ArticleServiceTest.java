package com.app.cms.service;

import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

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
        final var articleToSave = Article.builder().title("test title").content("test content").build();
        given(articleRepository.save(any(Article.class))).willReturn(articleToSave.toBuilder().id(-1L).build());

        //when
        Article savedArticle =  articleService.createArticle(articleToSave);

        //then
        then(savedArticle.getId()).isNotNull();
        then(savedArticle.getId()).isEqualTo(-1L);
    }



}
