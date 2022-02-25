package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

/**
 * 3.1.3. Синхронизация ресурсов.
 * 1. Visibility. Общий ресурс вне критической секции [#1102].
 * Задание. Код класс парсера файла.
 * 2. Поправьте код с ошибками в коде.
 * Избавиться от get set за счет передачи File в конструктор.
 * Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * Методы getContent написаны в стиле копипаста. Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.02.2022
 */
public final class ParseFile {
    private final File file;
    private static final int BUFFER = 1024;
    private static final Logger LOG = LoggerFactory.getLogger(ParseFile.class.getName());

    public ParseFile(final File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) {
        LOG.info("Чтение файла {}", file);
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            LOG.error("Не удалось выполнить операцию", e.getCause());
        }
        return output.toString();
    }
}
