package com.app.cms.service;

import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleConverter articleConverter;


    public ArticleService(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    public Article saveArticle(Article article) {
        article.setCreationDate(new Date());

        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        if (article.getId() == null)
            throw new IllegalArgumentException("Article id must be set");

        return articleRepository.save(article);
    }

    public void updateArticlePartially(Long articleId, Article articleWithChanges) {
        Article articleFromDb = articleRepository.getOne(articleId);


        articleRepository.save(articleFromDb);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

}
