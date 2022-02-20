package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.1. Threads
 * 1.2. Режим ожидания. [#231217]
 * Задание.
 * В методе main необходимо симулировать процесс загрузки.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class Wget {
    private static final Logger LOG = LoggerFactory.getLogger(Wget.class.getName());

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        for (int i = 0; i <= 100; i++) {
                            System.out.printf("\rLoading : %d %%", i);
                            Thread.sleep(100);
                        }
                        System.out.println(System.lineSeparator() + "Loaded.");
                    } catch (InterruptedException e) {
                        LOG.error("Exception in log example", e);
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
