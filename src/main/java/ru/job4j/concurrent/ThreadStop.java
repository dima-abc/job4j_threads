package ru.job4j.concurrent;

/**
 * 3.1.1. Threads
 * 3. Прерывание нити [#1019]
 * Пример. 3. Прерывание нити [#1019 #267516]
 * Пример. 3.1. Прерывание блокированной нити. [#267413].
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
                        try {
                            System.out.println(count++);
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }
}
