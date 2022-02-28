package ru.job4j.waitnotify.control;

import org.junit.Test;

/**
 * 3.1.4. Wait, Notify, NotifyAll
 * 0. Управление нитью через wait. [#6858]
 * Пример. https://www.baeldung.com/java-wait-notify
 * Пример демонстрирует отправку и прем пакета.
 * Получатель не может принять пакет пока отправитель его отправляет.
 * Отправитель не может отправить пакет пока получатель принимает предыдущий пакет.
 * Test демонстрация поведения.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
public class DataTest {

    @Test
    public void whenDataReceiverSend() {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        Thread receiver = new Thread(new Receiver(data));
        sender.start();
        receiver.start();
    }
}