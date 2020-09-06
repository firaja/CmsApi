package com.app.cms.valueobject.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class AuthorTest {

    @Test
    public void shouldCreateAuthor() {
        // given, when
        var author = Author.of("author");

        //then
        assertThat(author.getValue()).isEqualTo("author");
    }

    @Test
    public void shouldCreateAuthor_withLength150() {
        // given, when
        var author = Author.of(new String(new char[150]));

        //then
        assertThat(author.getValue()).isEqualTo(new String(new char[150]));
    }

    @Test
    public void shouldNotCreateAuthor_valueIsNull() {
        assertThatThrownBy(() ->
                Author.of(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateAuthor_valueIsTooLong() {
        assertThatThrownBy(() ->
                Author.of(new String(new char[151]))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
