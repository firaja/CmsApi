package com.app.cms.service;

import com.app.cms.entity.Article;
import com.app.cms.entity.valueobjects.article.Content;
import com.app.cms.entity.valueobjects.article.Title;
import com.app.cms.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
        final var articleToSave = Article.builder().title(new Title("test title")).content(new Content("test content")).build();
        given(articleRepository.save(any(Article.class))).willReturn(articleToSave.toBuilder().id(-1L).build());

        //when
        Article savedArticle = articleService.save(articleToSave);

        //then
        then(savedArticle.getId()).isNotNull();
        then(savedArticle.getId()).isEqualTo(-1L);
    }

    public void shouldUpdatePartially() {
        //given
           final var articleToSave = Article.builder().title(new Title("test title")).content(new Content("test content")).build();
        //     given(articleRepository.save(any(Article.class))).willReturn(articleToSave.toBuilder().id(-1L).build());

/*        Map<String, Object> valuesToUpdate = new HashMap<>();
        valuesToUpdate.put("title", "edited title");
        valuesToUpdate.put("content", "edited content");*/

        //when
        articleService.updatePartially(-1L, articleToSave);





        //then
        //  then(savedArticle.getId()).isNotNull();
        //   then(savedArticle.getId()).isEqualTo(-1L);
    }

}
