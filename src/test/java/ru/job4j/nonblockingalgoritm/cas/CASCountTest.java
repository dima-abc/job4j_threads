package ru.job4j.nonblockingalgoritm.cas;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.5. Non Blocking Algorithm
 * 0. CAS - операции [#6859]
 * Задание. Реализовать неблокирующий счетчик.
 * Test.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
public class CASCountTest {

    @Test
    public void whenIncrement2ThreadThen4() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread = new Thread(
                () -> {
                    count.increment();
                    count.increment();
                });
        Thread thread1 = new Thread(
                () -> {
                    count.increment();
                    count.increment();
                });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        assertThat(count.get(), is(4));
    }

    @Test
    public void whenIncrement3ThreadThen3() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread = new Thread(count::increment);
        Thread thread1 = new Thread(count::increment);
        Thread thread2 = new Thread(count::increment);
        thread.start();
        thread1.start();
        thread2.start();
        thread.join();
        thread1.join();
        thread2.join();
        assertThat(count.get(), is(3));
    }

}