package com.app.cms.valueobject.article;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class TitleTest {

    @Test
    public void shouldCreateTitle() {
        // given, when
        var title = Title.of("test title");

        //then
        assertThat(title.getValue()).isEqualTo("test title");
    }

    @Test
    public void shouldCreateTitle_withContentLength200() {
        // given, when
        var title = Title.of(new String(new char[200]));

        //then
        assertThat(title.getValue()).isEqualTo(new String(new char[200]));
    }

    @Test
    public void shouldNotCreateTitle_titleIsNull() {
        assertThatThrownBy(() -> {
            Title.of(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateTitle_titleIsTooLong() {
        assertThatThrownBy(() -> {
            Title.of(new String(new char[201]));
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
