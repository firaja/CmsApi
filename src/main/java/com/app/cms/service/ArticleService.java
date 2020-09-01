package com.app.cms.service;

import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.CommentRepository;
import com.app.cms.specification.ArticleSpecification2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleConverter articleConverter;

    private final CommentRepository commentRepository;

    public ArticleService(ArticleRepository articleRepository, ArticleConverter articleConverter, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.commentRepository = commentRepository;
    }

    public Article save(Article article) {
        article.setCreationDate(new Date());

        return articleRepository.save(article);
    }

    public Article update(Article article) {
        if (article.getId() == null)
            throw new IllegalArgumentException("Article id must be set");

        return articleRepository.save(article);
    }

    public void updatePartially(Long articleId, Map<String, Object> changedValues) {
        articleConverter.toEntity(changedValues);
        articleRepository.updatePartially(articleId, changedValues);
    }

    @Transactional
    public void delete(Long articleId) {
        commentRepository.deleteByArticleId(articleId);
        articleRepository.deleteById(articleId);
    }


    public Page<Article> get2(ArticleSpecification2 articleSpecification, Pageable pageable)
        {
            return articleRepository.findAll(articleSpecification, pageable);
        }

    public Page<Article> get(Specification<Article> spec, Pageable pageable) {
        return articleRepository.findAll(spec, pageable);
    }

}
