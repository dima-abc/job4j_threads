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
        for (int i = 0; i < SIZE; i++) {
            Thread thread = new MyThread(tasks);
            threads.add(thread);
            thread.start();
            LOGGER.info("{} thread start", thread.getName());
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

    /**
     * Метод shutdown() завершает все запущенные задачи.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
            LOGGER.info("{} остановлен", thread.getName());
        }
    }

    private class MyThread extends Thread {
        SimpleBlockingQueue<Runnable> target;

        public MyThread(SimpleBlockingQueue<Runnable> target) {
            this.target = target;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    target.poll().run();
                    LOGGER.info("{} task run", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
