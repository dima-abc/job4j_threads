package ru.job4j.pools;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067]
 * Пример.
 * Линейная сортировка массива объединением, через рекурсию.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.03.2022
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }


    private static int[] sort(int[] array, int from, int to) {
        if (from == to) {
            return new int[]{array[from]};
        }
        int mid = (from + to) / 2;

        return merge(
                sort(array, from, mid),
                sort(array, mid + 1, to)
        );
    }

    /**
     * Метод объединения двух отсортированных массивов, сортировкой.
     *
     * @param left  Array one
     * @param right Array two
     * @return left + right.
     */
    public static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] <= right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }
}
