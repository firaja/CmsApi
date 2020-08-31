package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.entity.Article;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.service.ArticleService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    /* @GetMapping
     @Cacheable("articles") */
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

/*    @GetMapping(params = { "page", "size" })
    public Page<ArticleDto> pagination2(@RequestParam("page") int page, @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
                                       HttpServletResponse response) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleDto> articleDtos = articleRepository.findAll(pageable).map(articleConverter::toDto);

        if (page > articleDtos.getTotalPages()) {
            throw new PaginationException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Article>(
                Article.class, uriBuilder, response, page, articleDtos.getTotalPages(), size));

        return articleDtos;
    }*/

    @GetMapping
    //  @Cacheable("articles")
    public Page<ArticleDto> get(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                @And({
                                        @Spec(path = "title.value", params = "title", spec = Like.class),
                                        @Spec(path = "content.value", params = "content", spec = Like.class),
                                        @Spec(path = "category.id", params = "category", spec = Equal.class),
                                        @Spec(path = "rating.value", params = "ratingValue", spec = Equal.class),
                                        @Spec(path = "rating.count", params = "ratingCount", spec = Equal.class),
                                        @Spec(path = "user.id", params = "user", spec = Equal.class),
                                        @Spec(path = "creationDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
                                }) Specification<Article> specification,
                                Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        return articleService.get(specification, pageable).map(articleConverter::toDto);
    }

}
