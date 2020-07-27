package com.app.cms.dto.mapper;

import com.app.cms.dto.ArticleDto;
import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
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
        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    public Article toEntity(ArticleDto announcementDto) {
        Article article = modelMapper.map(announcementDto, Article.class);

        article.setCategory(categoryRepository.getOne(announcementDto.getCategoryId()));
        article.setUser(userRepository.getOne(announcementDto.getUserId()));

        return article;
    }


}
