package com.app.cms.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldUpdatePartially() {
        //given
/*        final var articleToSave = Article.builder()
                .title(new Title("updated title1"))
                .content(new Content("updated content1"))
                .user(User.builder().id(-2L).build())
                .category(Category.builder().id(-2L).build())
           //     .rating(new Rating(2.3F, 6))
                .build();*/

        Map<String, Object> changedValues = new HashMap<>();
        changedValues.put("title", "this is new title");

        //when
        //    articleRepository.updatePartially(-1L, articleToSave);
        articleRepository.updatePartially(-1L, changedValues);

        //then
        var savedArticle = articleRepository.getOne(-1L);
        then(savedArticle.getTitle().getValue()).isEqualTo("updated title1");
        then(savedArticle.getContent().getValue()).isEqualTo("updated content1");
        then(savedArticle.getUser().getId()).isEqualTo(-2L);
        then(savedArticle.getCategory().getId()).isEqualTo(-2L);
        then(savedArticle.getRating().getValue()).isEqualTo(2.3F);
        then(savedArticle.getRating().getCount()).isEqualTo(6);
    }
}
