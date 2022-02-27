package ru.job4j.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Задание.
 * Разработайте класс, который блокирует выполнение по условию счетчика.
 * Переменная total содержит количество вызовов метода count().
 * Метод count изменяет состояние программы.
 * Это значит, что внутри метода count нужно вызывать метод notifyAll.
 * Нити, которые выполняют метод await, могут начать работу если поле count >= total.
 * Если оно не равно, то нужно перевести нить в состояние wait.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class CountBarrier {
    private final int total;
    private int count = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(CountBarrier.class.getName());

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        LOGGER.info("Job count ={} ", count);
        notifyAll();
    }

    public synchronized void await() {
        while (count < total) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread interrupted", e.getCause());
            }
        }
        LOGGER.info("Start await");
    }
}
