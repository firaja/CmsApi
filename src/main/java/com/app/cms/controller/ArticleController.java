package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.mapper.ArticleMapper;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleController(ArticleService articleService, ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto createArticle(@RequestBody @Valid ArticleDto article) {
        return articleMapper.convertToDto(articleService.createArticle(articleMapper.convertToEntity(article)));
    }

    @GetMapping(value = "/{articleId}")
    public ArticleDto getArticleById(@PathVariable @Min(1) Long articleId) {
        return articleMapper.convertToDto(articleRepository.getOne(articleId));
    }

    @GetMapping
    public List<ArticleDto> getAllArticles() {
        return articleRepository.findAll().stream().map(articleMapper::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{articleId}")
    public void deleteArticle(@PathVariable Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @PutMapping
    public ArticleDto updateArticle(@RequestBody @Valid ArticleDto articleDto) {
        return articleMapper.convertToDto(articleService.updateArticle(articleMapper.convertToEntity(articleDto)));
    }

}
