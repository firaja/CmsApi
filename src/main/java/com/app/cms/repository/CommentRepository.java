package com.app.cms.repository;

import com.app.cms.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryMethods {
    void deleteByArticleId(Long articleId);

    Collection<Comment> findByArticleId(Long articleId);
}
