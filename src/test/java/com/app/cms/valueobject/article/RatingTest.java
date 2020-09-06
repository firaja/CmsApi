package com.app.cms.valueobject.article;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class RatingTest {

    @Test
    public void shouldCreateRating() {
        // given, when
        var rating = Rating.of(1.5F, 2);

        //then
        assertThat(rating.getCount()).isEqualTo(2);
        assertThat(rating.getValue()).isEqualTo(1.5F);
    }

    @Test
    public void shouldCreateRating_zeroValues() {
        // given, when
        var rating = Rating.of(0F, 0);

        //then
        assertThat(rating.getCount()).isEqualTo(0);
        assertThat(rating.getValue()).isEqualTo(0);
    }

    @Test
    public void shouldNotCreateRating_ratingValueIsNegative() {
        assertThatThrownBy(() -> {
            Rating.of(-1.5F, 2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateRating_ratingValueIsNull() {
        assertThatThrownBy(() ->
                Rating.of(null, 2)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateRating_ratingValueIsMoreThant5() {
        assertThatThrownBy(() ->
                Rating.of(6F, 2)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldCreateRating_ratingValueIs5() {
        // given, when
        var rating = Rating.of(5F, 1);

        //then
        assertThat(rating.getValue()).isEqualTo(5F);
    }

    @Test
    public void shouldNotCreateRating_ratingCountIsNegative() {
        assertThatThrownBy(() ->
                Rating.of(1.5F, -2)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreateRating_ratingCountIsNull() {
        assertThatThrownBy(() ->
                Rating.of(1.5F, null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
