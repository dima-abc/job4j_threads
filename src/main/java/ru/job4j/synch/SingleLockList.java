package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 3.1.3. Синхронизация ресурсов
 * 4. ThreadSafe динамический список [#1105]
 * Задание. Создать коллекцию, которая будет корректно работать в многопоточной среде.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 27.02.2022
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList() {
        this.list = new ArrayList<>();
    }

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public synchronized void add(T value) {
        this.list.add(value);
    }

    public synchronized T get(int index) {
        return copy(this.list).get(index);
    }

    private List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
