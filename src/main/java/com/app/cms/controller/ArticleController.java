package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.service.ArticleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    public ArticleController(ArticleService articleService, ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleService = articleService;
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "articles", key = "#article.id")
    public ArticleDto createArticle(@RequestBody ArticleDto article) {
        return articleConverter.toDto(articleService.save(articleConverter.toEntity(article)));
    }

    @GetMapping(value = "/{articleId}")
    @Cacheable(value = "articles", key = "#articleId")
    public ArticleDto getArticleById(@PathVariable Long articleId) {
        return articleRepository.findById(articleId).map(articleConverter::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));
    }

    @GetMapping
    @Cacheable("articles")
    public CollectionModel<ArticleDto> getAllArticles() {
        Link link = linkTo(ArticleController.class).withSelfRel();
        return CollectionModel.of(articleRepository.findAll().stream().map(articleConverter::toDto).collect(Collectors.toList()), link);
    }

    @DeleteMapping(value = "/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "articles", key = "#articleId")
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.delete(articleId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CacheEvict(value = "articles", key = "#articleDto.id")
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleConverter.toDto(articleService.update(articleConverter.toEntity(articleDto)));
    }

    @PatchMapping(value = "/{articleId}", consumes = "application/json-patch+json")
    @CacheEvict(value = "articles", key = "#articleId")
    public void updateArticle(@PathVariable Long articleId, @RequestBody HashMap<String, Object> changedValues) {
        articleService.updatePartially(articleId, changedValues);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity collectionOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.OPTIONS).build();
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.OPTIONS)
    public ResponseEntity singularOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.OPTIONS).build();
    }

    // TODO pagination

}
