package com.app.cms.dto.converter;

import com.app.cms.controller.ArticleController;
import com.app.cms.dto.ArticleDto;
import com.app.cms.entity.Article;
import com.app.cms.entity.User;
import com.app.cms.entity.values.article.Content;
import com.app.cms.entity.values.article.Rating;
import com.app.cms.entity.values.article.Title;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            //      articleDto.setRating(article.getRating().getValue());
            //     articleDto.setRatingCount(article.getRating().getCount());
            //         articleDto.setRatingCount(article.getRating().;
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

        article.setTitle(new Title(articleDto.getTitle()));
        article.setContent(new Content(articleDto.getContent()));
        article.setRating(new Rating(articleDto.getRating(), articleDto.getRatingCount()));

        return article;
    }

    public User toEntity(Map<String, Object> objectAsMap) {
        return jacksonModelMapper.convertValue(objectAsMap, User.class);
    }
}
