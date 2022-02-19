package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class TriggerTest {
    @Test
    public void whenGetIntThenOne() {
        assertThat(1, is(new Trigger().getInt()));
    }
}