package com.app.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends RepresentationModel<CommentDto> {
    private Long id;

    private String content;

    private String author;

    private Long articleId;

    private Date creationDate;
}
