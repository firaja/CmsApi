package com.app.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 4000)
    private String content;

    @Past
    @NotNull
    @Column(updatable = false)
    private Date creationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)

    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Integer rate;

}
