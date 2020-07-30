package com.app.cms.dto.converter;

import com.app.cms.dto.CommentDto;
import com.app.cms.entity.Comment;
import com.app.cms.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter implements ObjectConverter<Comment, CommentDto> {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    public CommentConverter(ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommentDto toDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setArticle(articleRepository.getOne(commentDto.getArticleId()));

        return comment;
    }
}
