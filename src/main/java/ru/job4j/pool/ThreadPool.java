package ru.job4j.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.waitnotify.prodcons.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * 3.1.6. Пулы
 * 1. Реализовать ThreadPool [#1099]
 * Задание. Реализуйте требуемый в задании функционал.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 03.03.2022
 */
public class ThreadPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPool.class.getName());
    private static final int SIZE = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        this.initThread();
    }

    private void initThread() {
        for (int i = 0; i < SIZE; i++) {
            threads.add(new Thread());
            LOGGER.info("Создан {}", threads.get(i).getName());
        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Thread interrupted", e.getCause());
        }
    }

    public void treadStart() {
        while (!tasks.isEmpty()) {
            for (Thread thread : threads) {
                if (!thread.isAlive()) {
                    try {
                        thread = new Thread(tasks.poll());
                        LOGGER.info("{} start", thread.getName());
                        thread.start();
                        thread.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        LOGGER.error("Thread interrupted", e.getCause());
                    }
                    break;
                }
            }
        }
    }

    /**
     * Метод shutdown() завершает все запущенные задачи.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            LOGGER.info("Начата остановка {}", thread.getName());
            while (thread.isAlive()) {
                try {
                    thread.join();
                    thread.interrupt();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error("Thread interrupted", e.getCause());
                }
            }
            LOGGER.info("{} остановлен", thread.getName());
        }
    }
}
