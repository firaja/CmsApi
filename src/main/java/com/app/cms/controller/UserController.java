package com.app.cms.controller;

import com.app.cms.dto.ArticleDto;
import com.app.cms.dto.UserDto;
import com.app.cms.dto.converter.ArticleConverter;
import com.app.cms.dto.converter.UserConverter;
import com.app.cms.repository.UserRepository;
import com.app.cms.service.ArticleService;
import com.app.cms.service.UserService;
import com.app.cms.specification.article.ArticleWithUser;
import com.app.cms.specification.user.UserSpecification;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserConverter userConverter;
    private final ArticleService articleService;
    private final ArticleConverter articleConverter;

    public UserController(UserRepository userRepository, UserService userService, UserConverter userConverter, ArticleService articleService, ArticleConverter articleConverter) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userConverter = userConverter;
        this.articleService = articleService;
        this.articleConverter = articleConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userConverter.toDto(userService.save(userConverter.toEntity(userDto)));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userConverter.toDto(userService.save(userConverter.toEntity(userDto)));
    }

    @PatchMapping(value = "/{userId}")
    public void updateUserPassword(@PathVariable Long userId, UserDto userDto) {
        userService.saveUserPartially(userId, userConverter.toMap(userDto));
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userConverter.toDto(userRepository.getOne(userId));
    }

    @GetMapping(value = "/{userId}/articles")
    public Page<ArticleDto> getUserArticles(@PathVariable Long userId, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                            Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return articleService.get(new ArticleWithUser(userId), pageable).map(articleConverter::toDto);
    }

    @GetMapping
    @Cacheable("users")
    public Page<UserDto> getUsers(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                     UserSpecification specification, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(specification, pageable).map(userConverter::toDto);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

    // TODO patch options cache? pagination


}

