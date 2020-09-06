package com.app.cms.valueobject.article;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class CreationDateTest {

    @Test
    public void shouldCreateCreationDate() {
        // given, when
        var date = LocalDate.now();
        CreationDate creationDate = CreationDate.of(LocalDate.now());

        //then
        assertThat(creationDate.getValue()).isEqualTo(date);
    }

    @Test
    public void shouldNotCreateCreationDate_dateIsNull() {
        assertThatThrownBy(() ->
                CreationDate.of(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
