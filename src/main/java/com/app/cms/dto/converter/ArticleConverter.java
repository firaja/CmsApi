package com.app.cms.dto.converter;

import com.app.cms.controller.ArticleController;
import com.app.cms.dto.ArticleDto;
import com.app.cms.entity.Article;
import com.app.cms.entity.valueobjects.article.Content;
import com.app.cms.entity.valueobjects.article.Rating;
import com.app.cms.entity.valueobjects.article.Title;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ArticleConverter implements ObjectConverter<Article, ArticleDto> {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final com.fasterxml.jackson.databind.ObjectMapper jacksonModelMapper;

    public ArticleConverter(ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository, ObjectMapper jacksonModelMapper) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.jacksonModelMapper = jacksonModelMapper;
    }

    @Override
    public ArticleDto toDto(Article article) {
        var articleDto = new ArticleDto();
        articleDto.setUserId(article.getUser().getId());
        articleDto.setCategoryId(article.getCategory().getId());
        articleDto.setId(article.getId());
        articleDto.setCreationDate(article.getCreationDate());

        if (article.getContent() != null)
            articleDto.setContent(article.getContent().getValue());

        if (article.getRating() != null) {
            MoreObjects.firstNonNull()
            ObjectUtils.defaultIfNull(articleDto::setRatingValue, null);
            //          articleDto.setRatingValue(article.getRating().getValue());
            articleDto.setRatingCount(article.getRating().getCount());
        }

        if (article.getTitle() != null)
            articleDto.setTitle(article.getTitle().getValue());

        articleDto.add(
                linkTo(methodOn(ArticleController.class).getArticleById(article.getId())).withSelfRel(),
                linkTo(methodOn(ArticleController.class).getAllArticles()).withRel("articles"));

        return articleDto;
    }

    @Override
    public Article toEntity(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);

        article.setCategory(categoryRepository.getOne(articleDto.getCategoryId()));
        article.setUser(userRepository.getOne(articleDto.getUserId()));

        article.setTitle(Title.of(articleDto.getTitle()));
        article.setContent(Content.of(articleDto.getContent()));
        article.setRating(Rating.of(articleDto.getRatingValue(), articleDto.getRatingCount()));


        return article;
    }

    public Article toEntity(Map<String, Object> objectAsMap) {
        var articleDto = jacksonModelMapper.convertValue(objectAsMap, ArticleDto.class);
        return toEntity(articleDto);
    }
}

/*
class ClassUtils<T> {

    protected ClassUtils() { }

    public static  void setIfNotNull(final Supplier getter, final Consumer setter) {

        T t = getter.get();

        if (null != t) {
            setter.accept(t);
        }
    }
}
*/
