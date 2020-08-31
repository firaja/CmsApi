package com.app.cms.service;

import com.app.cms.entity.Article;
import com.app.cms.entity.valueobjects.article.Content;
import com.app.cms.entity.valueobjects.article.Title;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private ArticleService articleService;

    @Test
    public void shouldCreateArticle() {
        //given
        final var articleToSave = Article.builder().title(Title.of("test title")).content(Content.of("test content")).build();
        given(articleRepository.save(any(Article.class))).willReturn(articleToSave.toBuilder().id(-1L).build());

        //when
        Article savedArticle = articleService.save(articleToSave);

        //then
        then(savedArticle.getId()).isNotNull();
        then(savedArticle.getId()).isEqualTo(-1L);
    }

    @Test
    public void shouldDeleteArticle() {
        //when
        articleService.delete(-1L);

        //then
        verify(articleRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    public void shouldUpdateArticle() {
        //when
        articleService.update(Article.builder().id(-1L).build());

        //then
        verify(articleRepository, times(1)).save(any());
    }

    @Test
    public void shouldReturnArticleByCriteria() {
        //given


        //when

        //then
    }
}
