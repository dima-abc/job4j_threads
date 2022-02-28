package ru.job4j.waitnotify.prodcons;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * SimpleBlockingQueue реализация блокирующей очереди.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBlockingQueue.class.getName());
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int sizeQueue;

    public SimpleBlockingQueue() {
        this.sizeQueue = 5;
    }

    public SimpleBlockingQueue(int sizeQueue) {
        this.sizeQueue = sizeQueue;
    }

    /**
     * Метод вставляет новый элемент в конец очередь.
     * Если очередь заполнена поток ожидает освобождения.
     *
     * @param value Value.
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= sizeQueue) {
            wait();
        }
        LOGGER.info("Offer value: {}", value);
        queue.offer(value);
        notifyAll();
    }

    /**
     * Метод удаляет элемент в начале очереди.
     * Если очередь пустая, поток ожидает пополнения.
     *
     * @return Type.
     */
    public synchronized T poll() throws InterruptedException {
        T result;
        while (queue.size() == 0) {
            wait();
        }
        result = queue.poll();
        LOGGER.info("Poll value: {}", result);
        notifyAll();
        return result;
    }
}
