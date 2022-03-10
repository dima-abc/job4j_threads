package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool
 * Test примера. Поиск элемента в массиве рекурсивно и линейно.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 10.03.2022
 */
public class SearchIndexTest {

    @Test
    public void whenLineSearchObject() {
        Integer key = 11;
        Integer[] integers = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int result = SearchIndex.getIndex(integers, key);
        int expected = 10;
        assertThat(result, is(expected));
    }

    @Test
    public void whenRecursSearchObject() {
        Integer key = 11;
        Integer[] integers = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int result = SearchIndex.getIndexRecurs(integers, key);
        int expected = 10;
        assertThat(result, is(expected));
    }
}