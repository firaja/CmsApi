package com.app.cms.dto;

import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 4000)
    private String content;

    @NotNull
    @Past
    private Date creationDate;

    @NotNull
    private Long userId;

    @NotNull
    private Long categoryId;

    private Integer rate;
}
