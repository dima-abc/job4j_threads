package ru.job4j.nonblockingalgoritm.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 3.1.5. Non Blocking Algorithm
 * 0. CAS - операции [#6859]
 * Задание. Реализовать неблокирующий счетчик.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int temp;
        do {
            temp = count.get();
        } while (!count.compareAndSet(temp, temp + 1));
    }

    public int get() {
        return count.get();
    }
}
