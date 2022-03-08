package ru.job4j.pool;

import org.junit.Test;
import ru.job4j.nonblockingalgoritm.cas.CASCount;

import static org.junit.Assert.*;

/**
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 04.03.2022
 */
public class ThreadPoolTest {
    @Test
    public void whenInit() throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        CASCount count = new CASCount();
        for (int i = 0; i < 8; i++) {
            pool.work(() -> {
                count.increment();
                System.out.println(count.get());
            });
        }
        Thread.sleep(5000);
        pool.shutdown();
        assertEquals(8, count.get());
    }
}