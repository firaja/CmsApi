package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.service.ArticleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ArticleDto createArticle(@RequestBody ArticleDto article) {
        return articleConverter.toDto(articleService.saveArticle(articleConverter.toEntity(article)));
    }

    @GetMapping(value = "/{articleId}")
    public ArticleDto getArticleById(@PathVariable Long articleId) {
        return articleRepository.findById(articleId).map(articleConverter::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Article not found"));
    }

    @GetMapping
    public CollectionModel<ArticleDto> getAllArticles() {
        Link link = linkTo(ArticleController.class).withSelfRel();
        return CollectionModel.of(articleRepository.findAll().stream().map(articleConverter::toDto).collect(Collectors.toList()), link);
    }

    @DeleteMapping(value = "/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleConverter.toDto(articleService.updateArticle(articleConverter.toEntity(articleDto)));
    }

    @PatchMapping(value = "/{articleId}", consumes = "application/json-patch+json")
    public void updateArticle(@PathVariable Long articleId, ArticleDto articleDto) {
        articleService.updateArticlePartially(articleId, articleConverter.toEntity(articleDto));
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity collectionOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.OPTIONS).build();
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.OPTIONS)
    public ResponseEntity singularOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS).build();
    }

}
