package com.app.cms.dto.mapper;

import com.app.cms.dto.ArticleDto;
import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.CategoryRepository;
import com.app.cms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ArticleMapper {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ArticleMapper(ModelMapper modelMapper, ArticleRepository articleRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArticleDto convertToDto(Article article) {
        ArticleDto articleDto = modelMapper.map(article, ArticleDto.class);

        return articleDto;
    }

    public Article convertToEntity(ArticleDto announcementDto) {
        Article article = modelMapper.map(announcementDto, Article.class);

        article.setCategory(categoryRepository.getOne(announcementDto.getCategoryId()));
        article.setUser(userRepository.getOne(announcementDto.getUserId()));

        return article;
    }


}
