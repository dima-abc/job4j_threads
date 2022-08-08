package ru.job4j.leftrighttasktest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.requireNonNull;

/**
 * Тестовое задание по многопоточности Java.
 * Изменить код так, чтобы гарантированно печатало
 * Left
 * Right
 * left
 * Right
 * До бесконечно
 * Leg класс описывающий работу потока.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.08.2022
 */
public class Leg extends Thread {
    private final String name;
    private final Object monitor;

    public Leg(final String name, Object monitor) {
        this.name = requireNonNull(name);
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                System.out.println(name);
                monitor.notify();
                try {
                    monitor.wait();
                    Leg.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
