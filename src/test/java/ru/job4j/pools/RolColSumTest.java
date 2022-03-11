package ru.job4j.pools;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

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
    private int[][] matrix3x3 = new int[3][3];
    private int[][] matrix20x20 = new int[20][20];

    @Before
    public void getMatrix() {
        int value = 1;
        for (int i = 0; i < matrix3x3.length; i++) {
            for (int j = 0; j < matrix3x3.length; j++) {
                matrix3x3[i][j] = value++;
            }
        }
        value = 1;
        for (int i = 0; i < matrix20x20.length; i++) {
            for (int j = 0; j < matrix20x20.length; j++) {
                matrix20x20[i][j] = value++;
            }
        }
    }

    /**
     * Последовательный тест1.
     */
    @Test
    public void whenLineRolColSumMatrix3x3() {
        RolColSum.Sums[] result = RolColSum.sum(matrix3x3);
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        assertThat(result, is(expected));
    }

    /**
     * Последовательный тест1.
     */
    @Test
    public void whenLineRolColSumMatrix20x20() {
        RolColSum.Sums[] result = RolColSum.sum(matrix20x20);
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(210, 3820),
                new RolColSum.Sums(610, 3840),
                new RolColSum.Sums(1010, 3860),
                new RolColSum.Sums(1410, 3880),
                new RolColSum.Sums(1810, 3900),
                new RolColSum.Sums(2210, 3920),
                new RolColSum.Sums(2610, 3940),
                new RolColSum.Sums(3010, 3960),
                new RolColSum.Sums(3410, 3980),
                new RolColSum.Sums(3810, 4000),
                new RolColSum.Sums(4210, 4020),
                new RolColSum.Sums(4610, 4040),
                new RolColSum.Sums(5010, 4060),
                new RolColSum.Sums(5410, 4080),
                new RolColSum.Sums(5810, 4100),
                new RolColSum.Sums(6210, 4120),
                new RolColSum.Sums(6610, 4140),
                new RolColSum.Sums(7010, 4160),
                new RolColSum.Sums(7410, 4180),
                new RolColSum.Sums(7810, 4200)
        };
        assertThat(result, is(expected));
    }

    /**
     * Асинхронный тест 1
     *
     * @throws ExecutionException   exception.
     * @throws InterruptedException exception.
     */
    @Test
    public void whenAsyncRolColSumMatrix3x3() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] result = RolColSum.asyncSum(matrix3x3);
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        assertThat(result, is(expected));
    }

    /**
     * Асинхронный тест 2
     *
     * @throws ExecutionException   exception.
     * @throws InterruptedException exception.
     */
    @Test
    public void whenAsyncRolColSumMatrix20x20() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] result = RolColSum.asyncSum(matrix20x20);
        RolColSum.Sums[] expected = new RolColSum.Sums[]{
                new RolColSum.Sums(210, 3820),
                new RolColSum.Sums(610, 3840),
                new RolColSum.Sums(1010, 3860),
                new RolColSum.Sums(1410, 3880),
                new RolColSum.Sums(1810, 3900),
                new RolColSum.Sums(2210, 3920),
                new RolColSum.Sums(2610, 3940),
                new RolColSum.Sums(3010, 3960),
                new RolColSum.Sums(3410, 3980),
                new RolColSum.Sums(3810, 4000),
                new RolColSum.Sums(4210, 4020),
                new RolColSum.Sums(4610, 4040),
                new RolColSum.Sums(5010, 4060),
                new RolColSum.Sums(5410, 4080),
                new RolColSum.Sums(5810, 4100),
                new RolColSum.Sums(6210, 4120),
                new RolColSum.Sums(6610, 4140),
                new RolColSum.Sums(7010, 4160),
                new RolColSum.Sums(7410, 4180),
                new RolColSum.Sums(7810, 4200)
        };
        assertThat(result, is(expected));
    }

}