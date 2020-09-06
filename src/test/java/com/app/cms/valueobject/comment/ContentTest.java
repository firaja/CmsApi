package com.app.cms.valueobject.comment;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class ContentTest {

    @Test
    public void shouldCreateContent() {
        // given, when
        Content content = Content.of("Content example");

        //then
        assertThat(content.getValue()).isEqualTo("Content example");
    }

    @Test
    public void shouldNotCreateContent_contentIsNull() {
        assertThatThrownBy(() ->
                Content.of(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateContent_contentIsTooShort() {
        assertThatThrownBy(() ->
                Content.of("")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateContent_contentIsTooLong() {
        assertThatThrownBy(() ->
                Content.of(new String(new char[501]))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldCreateContent_contentLength499() {
        // given, when
        Content content = Content.of(new String(new char[499]));

        //then
        assertThat(content.getValue()).isNotBlank();
    }
}
