package ru.job4j.pool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3.1.6. Пулы
 * 2. ExecutorService рассылка почты.
 * Пример.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.03.2022
 */
public class SimpleExecutor {
    public static void main(String[] args) {
        int sizeProc = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(sizeProc);
        for (int i = 0; i < sizeProc; i++) {
            pool.submit(() ->
                    System.out.println("Execute " + Thread.currentThread().getName()));
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }
}
