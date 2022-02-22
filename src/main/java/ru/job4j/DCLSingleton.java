package ru.job4j;

/**
 * 3.1.2. Общие ресурсы
 * 2. Модель памяти Java [#267917]
 * Задание. Исправить ошибку синхронизации в Singleton.
 * В задании демонстрируется Double Checked Locking & volatile.
 * Двойная проверка используется для экономии ресурсов.
 * Ошибка в отсутствии облегченной синхронизации поля ссылки класса.
 * Ошибка возникает из-за возможного сохранение поля как в кэше процессора,
 * так и в регистре процессора, в результате разные потоки будут считывать
 * поле с разным состоянием из разных участков памяти процессора.
 * Это ведет к ситуации называемой проблемой видимости.(share visible problem).
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 22.02.2022
 */
public final class DCLSingleton {
    private static volatile DCLSingleton instance;

    private DCLSingleton() {
    }

    public static DCLSingleton instanceOf() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
