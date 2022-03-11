package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626]
 * Задача.  1. Подсчитать суммы по строкам и столбцам квадратной матрицы.
 * 2. Реализовать последовательную версию программы.
 * 3. Реализовать асинхронную версию программы.
 * i - я задача считает сумму по i столбцу и i строке.
 * 4. Написать тесты
 * Test.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.03.2022
 */
public class RolColSumTest {
    /**
     * Последовательный тест1.
     */
    @Test
    public void whenLineSumMatrix3x3() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] result = RolColSum.sum(matrix);
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        assertThat(result, is(expected));
    }
}