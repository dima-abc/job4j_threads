package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool
 * Задание: Реализовать параллельный поиск индекса в массиве объектов.
 * В целях оптимизации, если размер массива не больше 10,
 * использовать обычный линейный поиск.
 * Метод поиска должен быть обобщенным.
 * Test.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 10.03.2022
 */
public class ParallelSearchIndexTest {

    @Test
    public void whenParallelSearchIndexIsArraySize20Then16() {
        Integer key = 17;
        Integer[] integers = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int expected = 16;
        int result = ParallelSearchIndex.myInvoke(integers, key, 0, integers.length - 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParallelSearchIndexIsArraySize9Then5() {
        Integer key = 6;
        Integer[] integers = new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9};
        int expected = 5;
        int result = ParallelSearchIndex.myInvoke(integers, key, 0, integers.length - 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParallelSearchIndexIsArraySize37Then31() {
        Integer[] integers = new Integer[37];
        Integer key = 32;
        for (int i = 0; i < 37; i++) {
            integers[i] = i + 1;
        }
        int expected = 31;
        int result = ParallelSearchIndex.myInvoke(integers, key, 0, integers.length - 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParallelSearchIndexIsArraySize60Then55() {
        Integer[] integers = new Integer[60];
        Integer key = 56;
        for (int i = 0; i < 60; i++) {
            integers[i] = i + 1;
        }
        int expected = 55;
        int result = ParallelSearchIndex.myInvoke(integers, key, 0, integers.length - 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenParallelSearchIndexIsArraySize200Then198() {
        Integer[] integers = new Integer[200];
        Integer key = 199;
        for (int i = 0; i < 200; i++) {
            integers[i] = i + 1;
        }
        int expected = 198;
        int result = ParallelSearchIndex.myInvoke(integers, key, 0, integers.length - 1);
        assertThat(result, is(expected));
    }
}