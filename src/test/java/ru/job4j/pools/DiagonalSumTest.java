package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626]
 * Пример.  Посчитать суммы элементов по диагоналям матрицы.
 * Диагоналей в матрице = 2 * N.
 * Test.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.03.2022
 */
public class DiagonalSumTest {
    @Test
    public void whenDiagonalSumMatrix() throws Exception {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[] result = DiagonalSum.asyncDiagonalSum(matrix);
        int[] expected = new int[]{34, 1, 7, 18, 34, 33, 27, 16};
        assertThat(result, is(expected));
    }
}