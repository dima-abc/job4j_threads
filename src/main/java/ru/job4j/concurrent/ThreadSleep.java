package ru.job4j.concurrent;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * 3.1.1. Threads
 * 1.2. Режим ожидания. [#231217]
 * Пример.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class ThreadSleep {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadSleep.class.getName());

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        Thread.sleep(3000);
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        LOG.error("Exception in log example", e);
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
