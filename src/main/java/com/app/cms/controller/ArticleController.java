package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.entity.Article;
import com.app.cms.error.type.PaginationException;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.service.ArticleService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    private final ApplicationEventPublisher eventPublisher;

    public ArticleController(ArticleService articleService, ArticleRepository articleRepository, ArticleConverter articleConverter, ApplicationEventPublisher eventPublisher) {
        this.articleService = articleService;
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.eventPublisher = eventPublisher;
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

    @GetMapping(params = { "page", "size" })
    public Page<ArticleDto> pagination(@RequestParam("page") int page, @RequestParam("size") int size, UriComponentsBuilder uriBuilder,
                                    HttpServletResponse response) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleDto> articleDtos = articleRepository.findAll(pageable).map(articleConverter::toDto);

      //  Page<ArticleDto> artDtos = articleRepository.findAll(pageable).map(articleConverter::toDto);
   //     articleRepository.findAll(pageable);

     //   Page<ArticleDto> resultPage = new PageImpl<>(articleDtos);
 //       Page<ArticleDto> resultPage = articleRepository.findAll(pageable).
        if (page > articleDtos.getTotalPages()) {
            throw new PaginationException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Article>(
                Article.class, uriBuilder, response, page, articleDtos.getTotalPages(), size));

        return articleDtos;
    }

    @Transactional
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ArticleDto>> get(
            @And({
                    @Spec(path = "manufacturer", params = "manufacturer", spec = Like.class),
                    @Spec(path = "model", params = "model", spec = Like.class),
                    @Spec(path = "country", params = "country", spec = In.class),
                    @Spec(path = "type", params = "type", spec = Like.class),
                    @Spec(path = "createDate", params = "createDate", spec = Equal.class),
                    @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
            }) Specification<ArticleDto> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers)
    {
        articleRepository.findAll
        Page<ArticleDto> articleDtos = articleRepository.findAll(pageable).map(articleConverter::toDto);
        final PagingResponse response = carService.get(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

}
