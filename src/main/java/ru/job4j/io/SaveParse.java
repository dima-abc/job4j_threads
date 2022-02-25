package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 3.1.3. Синхронизация ресурсов.
 * 1. Visibility. Общий ресурс вне критической секции [#1102].
 * SaveParse сохраняет данные в файл.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.02.2022
 */
public final class SaveParse {
    private final File file;
    private static final Logger LOG = LoggerFactory.getLogger(SaveParse.class);

    public SaveParse(final File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        LOG.info("Запись информации в файл {}", file);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, true))) {
            out.write(content.getBytes());
        } catch (IOException e) {
            LOG.error("Не удалось выполнить операцию", e.getCause());
        }
    }
}
