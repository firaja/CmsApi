package com.app.cms.entity;

import com.app.cms.entity.values.article.Content;
import com.app.cms.entity.values.article.CreationDate;
import com.app.cms.entity.values.article.Rating;
import com.app.cms.entity.values.article.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Title title;

    private Content content;

    @NotNull
    @Column(updatable = false)
    private CreationDate creationDate;

    private Rating rating;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
