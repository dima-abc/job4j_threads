package ru.job4j.pool.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 3.1.6. Пулы
 * 2. ExecutorService рассылка почты.
 * Задача.  Реализовать сервис для рассылки почты.
 * EmailNotification сервис по рассылке через ExecutorService.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.03.2022
 */
public class EmailNotification {
    private static final int SIZE_PROC = Runtime.getRuntime().availableProcessors();
    private final ExecutorService pool = Executors.newFixedThreadPool(SIZE_PROC);

    /**
     * Метод emailTo(User user),
     * должен через ExecutorService отправлять почту
     *
     * @param user User
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notificator %s to email %s", user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
        });
    }

    /**
     * Создание сообщения для пользователя.
     *
     * @param subject String
     * @param body    String
     * @param email   String
     */
    public void send(String subject, String body, String email) {

    }

    /**
     * Останавливает все задачи и закрывает потоки.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
