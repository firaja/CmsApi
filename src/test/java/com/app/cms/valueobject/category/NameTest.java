package com.app.cms.valueobject.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class NameTest {

    @Test
    public void shouldCreateName() {
        // given, when
        var name = Name.of("test name");

        //then
        assertThat(name.getValue()).isEqualTo("test name");
    }

    @Test
    public void shouldNotCreateName_nameIsTooLong() {
        assertThatThrownBy(() ->
                Name.of(new String(new char[151]))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldCreateName_withLength150() {
        // given, when
        var name = Name.of(new String(new char[150]));

        //then
        assertThat(name.getValue()).isEqualTo(new String(new char[150]));
    }

    @Test
    public void shouldNotCreateName_nameIsNull() {
        assertThatThrownBy(() ->
                Name.of(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
