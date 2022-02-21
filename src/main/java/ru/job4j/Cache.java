package ru.job4j;

/**
 * 3.1.2. Общие ресурсы
 * 1. Синхронизация общих ресурсов. [#1096]
 * Задание.
 * 1. Ниже приведен код. Это код содержит ошибку атомарности. Поправьте код, загрузите изменения в github.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 21.02.2022
 */
public final class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
