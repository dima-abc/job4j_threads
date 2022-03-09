package ru.job4j.pools;

import java.util.concurrent.RecursiveTask;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool [#315067]
 * Параллельная сортировка массива объединением,
 * при помощи ForkJoinPool.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.03.2022
 */
public class ParallelMergeSort extends RecursiveTask<int[]> {
    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (array.length <= 10) {
            return MergeSort.sort(array);
        }
        if (from == to) {
            return new int[]{array[from]};
        }
        int mid = (from + to) / 2;

        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to);

        leftSort.fork();
        rightSort.fork();

        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }
}
