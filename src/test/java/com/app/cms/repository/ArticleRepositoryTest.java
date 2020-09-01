package com.app.cms.repository;

import com.app.cms.entity.Article;
import com.app.cms.entity.valueobjects.article.Title;
import com.app.cms.specification.ArticleSpecification2;
import com.app.cms.specification.ArticleSpecificationImpl;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
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

    //@Spec(path="title.value", params="title", spec= Like.class) Specification<Article> articleByTitle;

    private ArticleSpecification2 articleSpecification2;

    @Test
    public void shouldReturnResultsUsingPagination() {
        //given
        Pageable pageable = PageRequest.of(0, 10);

        //when
        Page<Article> articles =  articleRepository.findAll(new ArticleSpecificationImpl("Article1 title"), pageable);

        //then
        assertThat(articles).isNotEmpty();
    }
}

/*public class ArticleSpecs {
    public static Specification<Article> testQuery(String cos) {
        return (root, query, criteriaBuilder) -> {
         //   ListJoin<Employee, Phone> phoneJoin = root.join(Employee_.phones);
            criteriaBuilder.equal(article. ,cos);
            return criteriaBuilder.equal(phoneJoin.get(Phone_.type), phoneType);
        };
    }
}*/


 class ArticleSpecification implements Specification<Article> {

    private List<SearchCriteria> list;

    public ArticleSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}


 class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

     public SearchOperation getOperation() {
        return operation;
    }

     public String getKey() {
         return key;
     }

     public Object getValue() {
         return value;
     }

     // getters and setters, toString(), ... (omitted for brevity)
}

 enum SearchOperation {
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN
}

