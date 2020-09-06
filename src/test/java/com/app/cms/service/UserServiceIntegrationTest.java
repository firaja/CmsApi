package com.app.cms.service;


import com.app.cms.entity.Article;
import com.app.cms.entity.Category;
import com.app.cms.entity.User;
import com.app.cms.error.type.ObjectHaveReferencedObjects;
import com.app.cms.repository.UserRepository;
import com.app.cms.valueobject.article.Content;
import com.app.cms.valueobject.article.Rating;
import com.app.cms.valueobject.article.Title;
import com.app.cms.valueobject.user.Email;
import com.app.cms.valueobject.user.Login;
import com.app.cms.valueobject.user.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Test
    @Transactional
    public void shouldUpdatePassword_byPartiallyUpdate() {
        //given
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("password", "Password657");
        userValues.put("passwordConfirm", "Password657");

        //when
        userService.saveUserPartially(-1L, userValues);

        //then
        assertThat(userRepository.getOne(-1L)).isNotNull();
        assertThat(userRepository.getOne(-1L).getPassword()).isEqualTo(Password.of("Password657".toCharArray(), "Password657".toCharArray()));
        assertThat(userRepository.getOne(-1L).getPassword()).isNotEqualTo(Password.of("Password22".toCharArray(), "Password22".toCharArray()));
    }

    @Test
    @Transactional
    public void shouldUpdateLogin_byPartiallyUpdate() {
        //given
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("login", "login5747");

        //when
        userService.saveUserPartially(-1L, userValues);

        //then
        assertThat(userRepository.getOne(-1L)).isNotNull();
        assertThat(userRepository.getOne(-1L).getLogin()).isEqualTo(Login.of("login5747"));
        assertThat(userRepository.getOne(-1L).getLogin()).isNotEqualTo(Login.of("lo3434747"));
    }

    @Test
    @Transactional
    public void shouldUpdateEmail_byPartiallyUpdate() {
        //given
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("email", "email5653@gmail.com");

        //when
        userService.saveUserPartially(-1L, userValues);

        //then
        assertThat(userRepository.getOne(-1L)).isNotNull();
        assertThat(userRepository.getOne(-1L).getEmail()).isEqualTo(Email.of("email5653@gmail.com"));
        assertThat(userRepository.getOne(-1L).getEmail()).isNotEqualTo(Email.of("em4343@gmail.com"));
    }


    @Test
    @Transactional
    public void shouldCreateUser() {
        //given
        var password = Password.of("Password582048".toCharArray(), "Password582048".toCharArray());
        var email = Email.of("mail787565@mail.com");
        var login = Login.of("login787776");
        var user = User.builder().login(login).email(email).password(password).build();

        //when
        userService.save(user);

        //then
        assertThat(userRepository.existsByLogin(user.getLogin())).isTrue();
        assertThat(userRepository.getOne(user.getId()).getPassword()).isEqualTo(password);
        assertThat(userRepository.getOne(user.getId()).getEmail()).isEqualTo(email);
        assertThat(userRepository.getOne(user.getId()).getLogin()).isEqualTo(login);
    }

    @Test
    public void shouldDeleteUser() {
        //given
        var password = Password.of("Pass459230fk".toCharArray(), "Pass459230fk".toCharArray());
        var email = Email.of("434s459230fk@mail.com");
        var login = Login.of("loginh56h34f");
        var user = User.builder().login(login).email(email).password(password).build();

        //when
        userService.save(user);
        userService.delete(user.getId());

        //then
        assertThat(userRepository.existsByLogin(user.getLogin())).isFalse();
    }

    @Test
    public void shouldNotDeleteUser_userHaveArticles() {
        //given
        var password = Password.of("Pass459230fk".toCharArray(), "Pass459230fk".toCharArray());
        var email = Email.of("656532323@mail.com");
        var login = Login.of("login38923umf8j34");
        var user = User.builder().login(login).email(email).password(password).build();

        var article = Article.builder().category(Category.builder().id(-1L).build()).user(user)
                .rating(Rating.of(0F, 0)).title(Title.of("title")).content(Content.of("content")).build();

        //when
        userService.save(user);
        articleService.save(article);

        assertThatThrownBy(() -> {
            userService.delete(user.getId());
        }).isInstanceOf(ObjectHaveReferencedObjects.class);
    }
}
