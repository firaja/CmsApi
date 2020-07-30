package com.app.cms.controller;

import com.app.cms.dto.CommentDto;
import com.app.cms.dto.converter.CommentConverter;
import com.app.cms.repository.CommentRepository;
import com.app.cms.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final CommentConverter commentConverter;

    public CommentController(CommentRepository commentRepository, CommentService commentService, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentService = commentService;
        this.commentConverter = commentConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestBody @Valid CommentDto commentDto) {
        return commentConverter.toDto(commentService.saveComment(commentConverter.toEntity(commentDto)));
    }

    @GetMapping(value = "/{commentId}")
    public CommentDto getComment(@PathVariable Long commentId) {
        return commentConverter.toDto(commentRepository.getOne(commentId));
    }

    @DeleteMapping(value = "/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    @PutMapping
    public CommentDto updateComment(@RequestBody @Valid CommentDto commentDto) {
        return commentConverter.toDto(commentService.saveComment(commentConverter.toEntity(commentDto)));
    }


}
