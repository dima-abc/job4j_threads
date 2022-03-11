package ru.job4j.pools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool
 * Задание: Реализовать параллельный поиск индекса в массиве объектов.
 * В целях оптимизации, если размер массива не больше 10,
 * использовать обычный линейный поиск.
 * Метод поиска должен быть обобщенным.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 10.03.2022
 */
public class ParallelSearchIndex extends RecursiveTask<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParallelSearchIndex.class.getName());
    private static final int THRESHOLD = 10;
    private final Object[] objects;
    private final Object value;
    private final int from;
    private final int to;

    public ParallelSearchIndex(final Object[] objects, final Object value, final int from, final int to) {
        this.objects = objects;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public static Integer myInvoke(Object[] objects, Object value, int from, int to) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex(objects, value, from, to));
    }

    @Override
    protected Integer compute() {
        if (to - from <= THRESHOLD) {
            return searchLine(from, to);
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex left = new ParallelSearchIndex(objects, value, from, mid);
        ParallelSearchIndex right = new ParallelSearchIndex(
                objects, value, mid + 1, to);
        left.fork();
        right.fork();
        int l = left.join();
        int r = right.join();
        return Math.max(r, l);
    }

    /**
     * Метод реализует линейный поиск в диапазоне.
     *
     * @param from From
     * @param to   To
     * @return Int
     */
    private Integer searchLine(int from, int to) {
        LOGGER.info("Job the {}", Thread.currentThread().getName());
        for (int j = from; j < to; j++) {
            if (value.equals(objects[j])) {
                return j;
            }
        }
        return -1;
    }
}
