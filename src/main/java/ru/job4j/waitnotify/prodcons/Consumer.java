package ru.job4j.waitnotify.prodcons;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Consumer реализация удаления данных из очереди.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */
public class Consumer<T> {
    private final SimpleBlockingQueue<T> queue;

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public T poll() {
        return queue.poll();
    }
}
