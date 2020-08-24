package com.app.cms.repository;

import com.app.cms.error.type.QueryParamsNotSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void shouldUpdatePartially_authorAndContent() {
        //given
        Map<String, Object> changedValues = new HashMap<>();
        changedValues.put("content", "content 4346");
        changedValues.put("author", "author 565");

        //when
        commentRepository.updatePartially(-1L, changedValues);

        //then
        var updatedComment = commentRepository.getOne(-1L);
        then(updatedComment.getContent().getValue()).isEqualTo("content 4346");
        then(updatedComment.getAuthor().getValue()).isEqualTo("author 565");
    }

    @Test
    public void shouldThrowException_queryParamsAreNotSet() {
        //given
        Map<String, Object> changedValues = new HashMap<>();

        //when
        assertThatThrownBy(() -> {
            commentRepository.updatePartially(-1L, changedValues);
        }).isInstanceOf(QueryParamsNotSet.class);
    }
}
