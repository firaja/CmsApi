package com.app.cms.dto;

import com.app.cms.validator.constraint.PasswordsEqual;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@PasswordsEqual(message = "Passwords are different")
public class UserPasswordDto {
    private Long userId;

    private char[] password;

    private char[] passwordConfirm;
}

