package ru.job4j.waitnotify.prodcons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Consumer реализация удаления данных из очереди.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */
public class Consumer<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class.getName());
    private final SimpleBlockingQueue<T> queue;

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public T poll() {
        T result = null;
        try {
            result = queue.poll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Throw interrupted", e.getCause());
        }
        return result;
    }
}
