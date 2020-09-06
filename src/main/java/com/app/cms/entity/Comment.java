package com.app.cms.entity;

import com.app.cms.valueobject.comment.Author;
import com.app.cms.valueobject.comment.Content;
import com.app.cms.valueobject.comment.CreationDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private Content content;

    private Author author;

    @NotNull
    @Column(updatable = false)
    private CreationDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article", nullable = false)
    private Article article;

}
