package ru.job4j.waitnotify.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Пример. https://www.baeldung.com/java-wait-notify
 * Пример демонстрирует отправку и прем пакета.
 * Получатель не может принять пакет пока отправитель его отправляет.
 * Отправитель не может отправить пакет пока получатель принимает предыдущий пакет.
 * Class Sender. Описывает поведение отправителя.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class Sender implements Runnable {
    private Data data;
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class.getName());

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String[] packets = {"First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"};
        for (String packet : packets) {
            data.send(packet);
            System.out.println("Send: " + packet);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread interrupted", e.getCause());
            }
        }
    }
}
