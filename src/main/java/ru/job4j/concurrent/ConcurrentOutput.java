package ru.job4j.concurrent;

/**
 * 3.1.1. Threads
 * 1. Запуск нити. Thread#start() [#1016]
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 20.02.2022
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        System.out.println(Thread.currentThread().getName());
        second.start();
    }
}
