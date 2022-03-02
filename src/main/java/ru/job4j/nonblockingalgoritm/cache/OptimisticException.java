package ru.job4j.nonblockingalgoritm.cache;

/**
 * 3.1.5. Non Blocking Algorithm
 * 1. Неблокирующий кеш [#4741]
 * OptimisticException если version у модели и в кеше одинаковы, то можно обновить.
 * Если нет, то выбросить OptimisticException.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
