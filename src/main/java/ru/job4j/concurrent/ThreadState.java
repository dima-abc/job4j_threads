package ru.job4j.concurrent;

/**
 * 3.1.1. Threads
 * 1.1. Состояние нити. [#229175]
 * Класс запускает нить и выводит ее состояние.
 * Задание:
 * 1. Поправьте класс ThreadState там образом, чтобы в нем создавалось две нити. Каждая нить должна вывести свое имя на консоль.
 * 2. Нить main должна дождаться завершения этих нитей и вывести на консоль сообщение, что работа завершена.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        System.out.println(first.getState());
        first.start();
        System.out.println(second.getState());
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            continue;
        }
        System.out.println(first.getState());
        System.out.println(second.getState());
        System.out.println("Work completed");
    }
}
