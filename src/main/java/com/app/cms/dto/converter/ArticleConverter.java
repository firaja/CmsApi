package com.app.cms.dto.converter;

import com.app.cms.dto.ArticleDto;
import com.app.cms.entity.Article;
import com.app.cms.entity.values.article.Content;
import com.app.cms.entity.values.article.CreationDate;
import com.app.cms.entity.values.article.Rating;
import com.app.cms.entity.values.article.Title;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ArticleConverter implements ObjectConverter<Article, ArticleDto> {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ArticleConverter(ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ArticleDto toDto(Article article) {
        var articleDto = new ArticleDto();
        articleDto.setUserId(article.getUser().getId());
        articleDto.setCategoryId(article.getCategory().getId());
        articleDto.setId(article.getId());
        articleDto.setCreationDate(article.getCreationDate().getValue());
        articleDto.setContent(article.getContent().getValue());
        articleDto.setRating(article.getRating().getValue());
        articleDto.setTitle(article.getTitle().getValue());

        return articleDto;
    }

    @Override
    public Article toEntity(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);

        article.setCategory(categoryRepository.getOne(articleDto.getCategoryId()));
        article.setUser(userRepository.getOne(articleDto.getUserId()));

        article.setTitle(new Title(articleDto.getTitle()));
        article.setContent(new Content(articleDto.getContent()));
        article.setRating(new Rating(articleDto.getRating()));
        article.setCreationDate(new CreationDate(articleDto.getCreationDate()));

        return article;
    }


}
