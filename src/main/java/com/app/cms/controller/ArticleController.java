package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.service.ArticleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<ArticleDto> getArticleById(@PathVariable Long articleId) {
   //     return new ResponseEntity<>(articleConverter.toDto(articleRepository.getOne(articleId)),HttpStatus.OK );

        return toEntityModel(articleConverter.toDto(articleRepository.getOne(articleId)));
    }

    @GetMapping
    public List<EntityModel<ArticleDto>> getAllArticles() {

     /*   linkTo(methodOn(ArticleController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(ArticleController.class).all()).withRel("employees"))).collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(ArticleController.class).all()).wit
                hSelfRel());*/
    return null;
     //   return articleRepository.findAll().stream().map(articleConverter::toDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleConverter.toDto(articleService.saveArticle(articleConverter.toEntity(articleDto)));
    }

    // dodac options 200 + body

    public EntityModel toEntityModel(ArticleDto articleDto) {


        return EntityModel.of(articleDto,
                linkTo(methodOn(ArticleController.class).getArticleById(articleDto.getId())).withSelfRel(),
                linkTo(methodOn(ArticleController.class).getAllArticles()).withRel("articles"));
    }

}
