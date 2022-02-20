package ru.job4j.concurrent;

/**
 * 3.1.1. Threads
 * 3. Прерывание нити [#1019]
 * Пример.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
