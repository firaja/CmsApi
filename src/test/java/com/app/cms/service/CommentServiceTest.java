package com.app.cms.service;

import com.app.cms.entity.Comment;
import com.app.cms.repository.ArticleRepository;
import com.app.cms.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommentServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void shouldCreateComment() {
        //given
        final var commentToSave = Comment.builder().content("content").build();
        given(commentRepository.save(any(Comment.class))).willReturn(commentToSave.toBuilder().id(-1L).build());

        //when
        var savedComment = commentService.saveComment(commentToSave);

        //then
        then(savedComment.getId()).isEqualTo(-1L);
        then(savedComment.getContent()).isEqualTo("content");
    }

    @Test
    public void shouldDeleteComment() {
        //given
        final var commentId = -1L;

        //when
        commentService.deleteComment(commentId);

        //then
        verify(commentRepository, times(1)).deleteById(any());
    }


}
