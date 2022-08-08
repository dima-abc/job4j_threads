package ru.job4j.leftrighttasktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Тестовое задание по многопоточности Java.
 * Изменить код так, чтобы гарантированно печатало
 * Left
 * Right
 * left
 * Right
 * До бесконечно
 * Robot исполнение задачи.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.08.2022
 */
public class Robot {
    private static final Logger LOG = LoggerFactory.getLogger(Robot.class.getSimpleName());
    private final Leg leftLeg;
    private final Leg rightLeg;

    public Robot() {
        Object monitor = new Object();
        this.leftLeg = new Leg("Left", monitor);
        this.rightLeg = new Leg("Right", monitor);
    }

    public void start() {
        rightLeg.start();
        leftLeg.start();
    }

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            robot.start();
            System.in.read();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
