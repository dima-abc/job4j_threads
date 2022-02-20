package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3.1.1. Threads
 * 3. Прерывание нити [#1019]
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class ConsoleProgress implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleProgress.class.getName());

    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgress());
            progress.start();
            Thread.sleep(2000);
            progress.interrupt();
        } catch (InterruptedException e) {
            LOG.error("Exception in log example", e);
        }
    }

    @Override
    public void run() {
        String[] process = new String[]{"-", "\\", "|", "/"};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (String s : process) {
                    System.out.print("\rLoad... " + s);
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.error("Exception in log example", e);
        }
    }
}