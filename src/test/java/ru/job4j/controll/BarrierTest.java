package ru.job4j.controll;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Test. Пример. Класс Barrier.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class BarrierTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarrierTest.class.getName());

    @Test
    public void whenBarrierWaitNotifyAll() {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    LOGGER.info("{} started", Thread.currentThread().getName());
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    LOGGER.info("{} started", Thread.currentThread().getName());
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}