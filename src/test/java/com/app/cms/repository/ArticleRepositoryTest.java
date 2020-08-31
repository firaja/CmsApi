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
    public void shouldUpdatePartially_titleAndContent() {
        //given
        Map<String, Object> changedValues = new HashMap<>();
        changedValues.put("title", "this is new title45452");
        changedValues.put("content", "updated content145676");

        //when
        articleRepository.updatePartially(-1L, changedValues);

        //then
        var savedArticle = articleRepository.getOne(-1L);
        then(savedArticle.getTitle().getValue()).isEqualTo("this is new title45452");
        then(savedArticle.getContent().getValue()).isEqualTo("updated content145676");
    }

    @Test
    public void shouldUpdatePartially_userAndCategory() {
        //given
        Map<String, Object> changedValues = new HashMap<>();
        changedValues.put("user", -2L);
        changedValues.put("category", -2L);

        //when
        articleRepository.updatePartially(-1L, changedValues);

        //then
        var savedArticle = articleRepository.getOne(-1L);
        then(savedArticle.getUser().getId()).isEqualTo(-2);
        then(savedArticle.getCategory().getId()).isEqualTo(-2);
    }

    @Test
    public void shouldUpdatePartially_ratingValueAndRatingCount() {
        //given
        Map<String, Object> changedValues = new HashMap<>();
        changedValues.put("ratingValue", 4.7F);
        changedValues.put("ratingCount", 7);

        //when
        articleRepository.updatePartially(-1L, changedValues);

        //then
        var savedArticle = articleRepository.getOne(-1L);
        then(savedArticle.getRating().getValue()).isEqualTo(4.7F);
        then(savedArticle.getRating().getCount()).isEqualTo(7);
    }

    @Test
    public void testCriteria() {
        //given
        //   CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        //    CriteriaQuery<User> query = builder.createQuery(User.class);
    }
}
