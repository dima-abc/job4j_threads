package ru.job4j.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * 3.1.1. Threads
 * 4. Скачивание файла с ограничением. [#144271]
 * Пример кода для скачивания файла с задержкой в одну секунду.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 21.02.2022
 */
public class FileDownload {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownload.class.getName());

    public static void main(String[] args) {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("./data/pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int batesRaed;
            int status = 0;
            while ((batesRaed = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, batesRaed);
                status += batesRaed;
                System.out.printf("\rLoading: %d bytes. time: %d", status, 1000);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
    }
}
