package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 3.1.3. Синхронизация ресурсов
 * 2. JCIP. Настройка библиотеки
 * Count класс реализации инкремента.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.02.2022
 */

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
