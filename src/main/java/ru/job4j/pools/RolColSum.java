package ru.job4j.pools;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 3.1.6. Пулы
 * 4. CompletableFuture [#361626]
 * Задача.  1. Подсчитать суммы по строкам и столбцам квадратной матрицы.
 * 2. Реализовать последовательную версию программы.
 * 3. Реализовать асинхронную версию программы.
 * i - я задача считает сумму по i столбцу и i строке.
 * 4. Написать тесты
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 11.03.2022
 */
public class RolColSum {
    /**
     * Последовательный подсчет сумм по строкам и столбцам квадратной матрицы.
     *
     * @param matrix Integer matrix.
     * @return Sums array.
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Sums(0, 0);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i].setRowSum(matrix[i][j] + result[i].getRowSum());
                result[j].setColSum(matrix[i][j] + result[j].getColSum());
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = getTask(matrix, i).get();
        }
        return result;
    }

    /**
     * Обобщенная асинхронная задача
     * для вычисления суммы одного столбца и одной строки.
     *
     * @param matrix Integer matrix
     * @param start  Int.
     * @return Sums.
     */
    private static CompletableFuture<Sums> getTask(int[][] matrix, int start) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums(0, 0);
            for (int i = 0; i < matrix.length; i++) {
                sums.setRowSum(matrix[start][i] + sums.getRowSum());
                sums.setColSum(matrix[i][start] + sums.getColSum());
            }
            return sums;
        });
    }

    /**
     * Модель данных для подсчета сумм строк и столбцов.
     */
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }

        @Override
        public String toString() {
            return "Sums{" + rowSum + ":" + colSum + '}';
        }
    }
}
