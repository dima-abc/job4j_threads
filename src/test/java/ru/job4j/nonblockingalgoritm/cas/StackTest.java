package ru.job4j.nonblockingalgoritm.cas;

import org.junit.Test;
import ru.job4j.nonblockingalgoritm.cas.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.5. Non Blocking Algorithm
 * 0. CAS - операции [#6859]
 * Пример.
 * Класс Stack реализует не потокобезопасную очередь LIFO.
 * Test.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
public class StackTest {

    @Test
    public void when3PushThen3Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

}