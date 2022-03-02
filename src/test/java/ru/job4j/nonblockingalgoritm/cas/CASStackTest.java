package ru.job4j.nonblockingalgoritm.cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
public class CASStackTest {

    @Test
    public void when3PushAnd3Poll() throws InterruptedException {
        CASStack<Integer> casStack = new CASStack<>();
        Thread thread1 = new Thread(
                () -> {
                    casStack.push(1);
                    casStack.push(1);
                });
        Thread thread2 = new Thread(
                () -> {
                    casStack.push(1);
                    casStack.push(1);
                });
        Thread thread3 = new Thread(
                () -> {
                    casStack.poll();
                    casStack.poll();
                    casStack.poll();
                });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        assertThat(1, is(casStack.poll()));
    }

}