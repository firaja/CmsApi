package com.app.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto extends RepresentationModel<ArticleDto> {

    private Long id;

    private String title;

    private String content;

    private Date creationDate;

    private Long userId;

    private Long categoryId;

    private Float ratingValue;

    private Integer ratingCount;
}
