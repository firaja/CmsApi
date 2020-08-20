package com.app.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {

    private Long id;

    private String login;

    private String email;

    private char[] password;

    private char[] passwordConfirm;
}
