package com.app.cms.entity;

import com.app.cms.entity.valueobjects.article.Content;
import com.app.cms.entity.valueobjects.article.Rating;
import com.app.cms.entity.valueobjects.article.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


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
    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Column(updatable = false)
    private Date creationDate;

    @Embedded
    private Rating rating;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
