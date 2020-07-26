package com.app.cms.service;

import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);

    }

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

}
