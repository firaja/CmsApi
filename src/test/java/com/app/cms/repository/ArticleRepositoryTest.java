package com.app.cms.repository;

import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import com.app.cms.entity.valueobjects.article.Content;
import com.app.cms.entity.valueobjects.article.Rating;
import com.app.cms.entity.valueobjects.article.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldUpdatePartially() {
        //given
        final var articleToSave = Article.builder()
                .title(new Title("updated title1"))
                .content(new Content("updated content1"))
                .user(User.builder().id(-2L).build())
                .category(Category.builder().id(-2L).build())
                .rating(new Rating(2.3F, 6))
                .build();

        //when
        articleRepository.updatePartially(-1L, articleToSave);

        //then
        var savedArticle = articleRepository.getOne(-1L);
        then(savedArticle.getTitle().getValue()).isEqualTo("updated title1");
        then(savedArticle.getContent().getValue()).isEqualTo("updated content1");
        then(savedArticle.getUser().getId()).isEqualTo(-2L);
        then(savedArticle.getCategory().getId()).isEqualTo(-2L);
        then(savedArticle.getRating().getRatingValue()).isEqualTo(2.3F);
        then(savedArticle.getRating().getRatingCount()).isEqualTo(6);
    }
}
