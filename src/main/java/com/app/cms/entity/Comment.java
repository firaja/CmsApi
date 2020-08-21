package com.app.cms.entity;

import com.app.cms.entity.valueobjects.comment.Author;
import com.app.cms.entity.valueobjects.comment.Content;
import com.app.cms.entity.valueobjects.comment.CreationDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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

    @Past
    @NotNull
    @Column(updatable = false)
    private CreationDate creationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

}
