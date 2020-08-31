package com.app.cms.entity;

import com.app.cms.entity.valueobjects.user.Email;
import com.app.cms.entity.valueobjects.user.Login;
import com.app.cms.entity.valueobjects.user.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Login login;

    @NotNull
    private Password password;

    @NotNull
    private Email email;


}
