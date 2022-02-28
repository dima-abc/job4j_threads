package ru.job4j.waitnotify.prodcons;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Producer реализация добавления данных в очередь.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */
public class Producer<T> {
    private final SimpleBlockingQueue<T> queue;

    public Producer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public synchronized void offer(T value) {
        queue.offer(value);
    }
}
