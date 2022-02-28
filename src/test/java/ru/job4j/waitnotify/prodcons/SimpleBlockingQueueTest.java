package ru.job4j.waitnotify.prodcons;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Test. SimpleBlockingQueue.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.02.2022
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenSimpleBlockingQueueProducerConsumerSize2() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Producer<Integer> producer = new Producer<>(queue);
        Consumer<Integer> consumer = new Consumer<>(queue);
        Thread threadProd = new Thread(
                () -> {
                    producer.offer(1);
                    producer.offer(2);
                    producer.offer(3);
                    producer.offer(4);
                });
        Thread threadCons = new Thread(
                () -> {
                    consumer.poll();
                    consumer.poll();
                });
        threadCons.start();
        threadProd.start();
        threadProd.join();
        threadCons.join();
        assertThat(3, is(queue.poll()));
        assertThat(4, is(queue.poll()));
    }

    @Test
    public void whenSimpleBlockingQueueProducerConsumerSize1() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Producer<Integer> producer = new Producer<>(queue);
        Consumer<Integer> consumer = new Consumer<>(queue);
        Thread threadProd = new Thread(
                () -> {
                    producer.offer(1);
                    producer.offer(2);
                    producer.offer(3);
                });
        Thread threadCons = new Thread(
                () -> {
                    consumer.poll();
                    consumer.poll();
                });
        threadCons.start();
        threadProd.start();
        threadProd.join();
        threadCons.join();
        assertThat(3, is(queue.poll()));
    }
}
