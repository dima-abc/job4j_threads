package ru.job4j.nonblockingalgoritm.cas;

import net.jcip.annotations.NotThreadSafe;

/**
 * 3.1.5. Non Blocking Algorithm
 * 0. CAS - операции [#6859]
 * Пример.
 * check-then-act
 * Класс Stack реализует не потокобезопасную очередь LIFO.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
@NotThreadSafe
public class Stack<T> {
    private Node<T> head;

    /**
     * Добавить элемент.
     *
     * @param value Type
     */
    public void push(T value) {
        Node<T> temp = new Node<>(value);
        if (head == null) {
            head = temp;
            return;
        }
        temp.next = head;
        head = temp;
    }

    /**
     * Удалить элемент.
     *
     * @return Type.
     */
    public T poll() {
        Node<T> temp = head;
        if (temp == null) {
            throw new IllegalStateException("Stack is empty");
        }
        head = temp.next;
        temp.next = null;
        return temp.value;
    }

    /**
     * Узел очереди.
     *
     * @param <T> Type.
     */
    private static final class Node<T> {
        private final T value;

        private Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}
