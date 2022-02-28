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
 * Class Receiver. Описывает поведение класса получателя.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class Receiver implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class.getName());
    private Data load;

    public Receiver(Data data) {
        this.load = data;
    }

    @Override
    public void run() {
        for (String receivedMessage = load.receive();
             !"End".equals(receivedMessage);
             receivedMessage = load.receive()) {
            System.out.println("Receiver: " + receivedMessage);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("Thread interrupted", e.getCause());
            }
        }
    }
}
