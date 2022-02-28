package ru.job4j.waitnotify.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Пример. Класс Barrier.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class Barrier {
    private static final Logger LOGGER = LoggerFactory.getLogger(Barrier.class.getName());
    private boolean flag = false;
    private final Object monitor = this;

    /**
     * Метод пробуждает все нити и устанавливает флаг на пробуждение.
     */
    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    /**
     * Метод усыпляет все нити и устанавливает флаг на засыпание.
     */
    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    /**
     * метод переводит вызывающую нить в состоянии ожидания.
     */
    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error("Сбой при засыпании нити", e.getCause());
                }
            }
        }
    }
}
