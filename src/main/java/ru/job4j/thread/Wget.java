package ru.job4j.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
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
    private static final long MILLIS = 1000;
    private static final long NANO = 1000_000;
    private static final Logger LOG = LoggerFactory.getLogger(Wget.class.getName());

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    /**
     * Метод расчета времени паузы.
     *
     * @param start  Start.
     * @param finish Finish.
     * @return Sleep.
     */
    private long getTimeSleep(long start, long finish) {
        long result = 0;
        long timeLoadBUFFER = (finish - start) / NANO;
        long timeLoadSpeed = BUFFER * MILLIS / speed;
        if (timeLoadBUFFER < timeLoadSpeed) {
            result = timeLoadSpeed - timeLoadBUFFER;
        }
        return result;
    }

    /**
     * Вывод статуса загрузки
     *
     * @param statusLoad Количество загруженных байт
     * @param sleep      Время задержки.
     * @param startTime  Начала скачивания.
     */
    private void outStatus(float statusLoad, long sleep, long startTime) {
        long time = (System.currentTimeMillis() - startTime) / MILLIS;
        System.out.printf("\rLoad file %.2f byte. Sleep %d. Time_total %d", statusLoad, sleep, time);
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(new File(url).getName())) {
            byte[] dataBuffer = new byte[BUFFER];
            int byteRead;
            float statusLoad = 0;
            long startTime = System.currentTimeMillis();
            long startLoad = System.nanoTime();
            while ((byteRead = in.read(dataBuffer, 0, BUFFER)) != -1) {
                fileOutputStream.write(dataBuffer, 0, byteRead);
                long finishLoad = System.nanoTime();
                long sleep = getTimeSleep(startLoad, finishLoad);
                statusLoad += byteRead;
                outStatus(statusLoad, sleep, startTime);
                Thread.sleep(sleep);
                statusLoad += byteRead;
                startLoad = System.nanoTime();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            LOG.error("Exception in log", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            LOG.error("Parameters is empty. {}", new IllegalArgumentException("Enter parameters."));
            throw new IllegalArgumentException("Enter parameters.");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
