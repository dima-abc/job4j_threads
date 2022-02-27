package ru.job4j.controll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Пример. https://www.baeldung.com/java-wait-notify
 * Пример демонстрирует отправку и прем пакета.
 * Получатель не может принять пакет пока отправитель его отправляет.
 * Отправитель не может отправить пакет пока получатель принимает предыдущий пакет.
 * Class Data. Описывает модель пакета который будет отправляться и получатся.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class Data {
    private static final Logger LOGGER = LoggerFactory.getLogger(Data.class.getName());
    private String packet;
    /**
     * True получатель спит.
     * False отправитель спит.
     */
    private boolean transfer = true;

    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread Interrupted", e.getCause());
            }
        }
        transfer = true;
        String returnPacket = packet;
        notifyAll();
        return returnPacket;
    }

    public synchronized void send(String packet) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread Interrupted", e.getCause());
            }
        }
        transfer = false;
        this.packet = packet;
        notifyAll();
    }
}
