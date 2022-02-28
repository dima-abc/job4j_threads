package ru.job4j.waitnotify.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.waitnotify.prodcons.SimpleBlockingQueue;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 2. Обеспечить остановку потребителя. [#66825]
 * Задача. Изменить код, так, что бы потребитель завершал свою работу.
 * Код нужно изменить в методе main.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */
public class ParallelSearch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParallelSearch.class.getName());

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            LOGGER.error("Throw interrupted", e.getCause());
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int i = 0; i != 5; i++) {
                        try {
                            queue.offer(i);
                            System.out.println(i);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            LOGGER.error("Throw interrupted", e.getCause());
                        }
                    }
                    consumer.interrupt();
                }).start();
    }
}
