package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626]
 * Пример.  Посчитать суммы элементов по диагоналям матрицы.
 * Диагоналей в матрице = 2 * N.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.03.2022
 */
public class DiagonalSum {

    /**
     * Метод создает карту асинхронных задач дял подсчета суммы диагоналей матрицы
     *
     * @param matrix Integer matrix.
     * @return Integer array.
     * @throws ExecutionException   exception.
     * @throws InterruptedException exception.
     */
    public static int[] asyncDiagonalSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] result = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        futures.put(0, getTask(matrix, 0, n - 1, n - 1));
        for (int r = 1; r <= n; r++) {
            futures.put(r, getTask(matrix, 0, r - 1, r - 1));
            if (r < n) {
                futures.put(2 * n - r, getTask(matrix, n - r, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            result[key] = futures.get(key).get();
        }
        return result;
    }

    /**
     * Асинхронная задача считает сумму заданной диагонали матрицы.
     *
     * @param data     Integer array.
     * @param startRow int.
     * @param endRow   int.
     * @param startCol int.
     * @return int.
     */
    public static CompletableFuture<Integer> getTask(int[][] data, int startRow,
                                                     int endRow, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col--];
            }
            return sum;
        });
    }
}
