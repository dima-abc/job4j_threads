package ru.job4j.shared.linked;

/**
 * 3.1.2. Общие ресурсы
 * 3. Immutable объекты [#267918]
 * Задача. Сделайте этот класс Immutable.
 * Правила создания Immutable объекта.
 * 1. Все поля отмечены final.
 * 2. Состояние объекта не изменяется после создания объекта.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 22.02.2022
 */
public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(final Node<T> next, final T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getValue() {
        return this.value;
    }
}
