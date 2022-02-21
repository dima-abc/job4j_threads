package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * 3.1.1. Threads
 * 4. Скачивание файла с ограничением. [#144271]
 * Задание.
 * 1. Разработайте каркас класса Wget.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 21.02.2022
 */
public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private static final int BUFFER = 1024;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    /**
     * Метод расчета времени паузы.
     *
     * @param speed  Speed.
     * @param start  Start.
     * @param finish Finish.
     * @return Sleep.
     */
    private int getTimeSleep(int speed, long start, long finish) {
        long result = 0;
        long timeLoad = (finish - start) / 1000_000;
        if (timeLoad < speed) {
            result = speed - timeLoad;
        }
        return (int) result;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("./data/pom_tmp.xml")) {
            byte[] dataBuffer = new byte[BUFFER];
            int byteRead;
            int statusLoad = 0;
            long startLoad = System.nanoTime();
            while ((byteRead = in.read(dataBuffer, 0, BUFFER)) != -1) {
                fileOutputStream.write(dataBuffer, 0, byteRead);
                long finishLoad = System.nanoTime();
                int sleep = getTimeSleep(speed, startLoad, finishLoad);
                Thread.sleep(sleep);
                statusLoad += byteRead;
                System.out.printf("\rLoad file %d byte. Sleep %d", statusLoad, sleep);
                startLoad = System.nanoTime();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
