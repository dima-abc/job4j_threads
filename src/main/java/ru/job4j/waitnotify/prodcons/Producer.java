package ru.job4j.waitnotify.prodcons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Producer реализация добавления данных в очередь.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */
public class Producer<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class.getName());
    private final SimpleBlockingQueue<T> queue;

    public Producer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public synchronized void offer(T value) {
        try {
            queue.offer(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Throw interrupted", e.getCause());
        }
    }
}
