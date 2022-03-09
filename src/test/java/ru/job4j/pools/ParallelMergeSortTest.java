package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067]
 * Параллельная сортировка массива объединением,
 * при помощи ForkJoinPool.
 * Test.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.03.2022
 */
public class ParallelMergeSortTest {

    @Test
    public void whenArraySizeTen() {
        int[] in = new int[]{9, 15, 2, 6, 11, 84, 10, 5, 7, 4};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] result = forkJoinPool.invoke(new ParallelMergeSort(in, 0, in.length - 1));
        int[] expected = new int[]{2, 4, 5, 6, 7, 9, 10, 11, 15, 84};
        assertThat(result, is(expected));
    }

    @Test
    public void whenArraySizeGaitTen() {
        int[] in = new int[]{
                99, 15, 44, 18, 2,
                1, 3, 54, 14, 22,
                13, 9, 0, 17, 33,
                55, 48, 31, 19,
                5, 7, 111, 39, 28};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] result = forkJoinPool.invoke(new ParallelMergeSort(in, 0, in.length - 1));
        int[] expected = new int[]{
                0, 1, 2, 3, 5,
                7, 9, 13, 14, 15,
                17, 18, 19, 22, 28,
                31, 33, 39, 44, 48,
                54, 55, 99, 111
        };
        assertThat(result, is(expected));
    }

}