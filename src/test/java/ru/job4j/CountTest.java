package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.3. Синхронизация ресурсов
 * 2. JCIP. Настройка библиотеки
 * Test. Count.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.02.2022
 */
public class CountTest {
    /**
     * Класс описывает нить со счетчиком.
     */
    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();

        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);

        first.start();
        second.start();
        first.join();
        second.join();

        assertThat(count.get(), is(2));
    }
}