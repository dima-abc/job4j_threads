package ru.job4j.controll;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Test. Задание.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class CountBarrierTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(CountBarrierTest.class.getName());

    @Test
    public void whetCountBarrier() {
        CountBarrier countBarrier = new CountBarrier(2);
        Thread master = new Thread(
                () -> {
                    LOGGER.info("{} started", Thread.currentThread().getName());
                    countBarrier.count();
                    countBarrier.count();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    countBarrier.count();
                    countBarrier.count();
                    countBarrier.count();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    LOGGER.info("{} started", Thread.currentThread().getName());
                    countBarrier.await();
                },
                "slave"
        );
        slave.start();
        master.start();
    }
}