package ru.job4j.ref;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

/**
 * 3.1.2. Общие ресурсы
 * 4. Thread без общих ресурсов [#267919]
 * Test.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 24.02.2022
 */
public class UserCacheTest {
    @Test
    public void whenThreadSafeAddUserThenTrue() throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> user.setName("rename"));
        first.start();
        first.join();
        assertThat("name", is(cache.findById(1).getName()));
        assertThat("rename", is(user.getName()));
    }

    @Test
    public void whenThreadFindAllThenTrue() throws InterruptedException {
        UserCache cache = new UserCache();
        User user1 = User.of("name1");
        User user2 = User.of("name2");
        cache.add(user1);
        cache.add(user2);
        List<User> list = cache.findAll();
        Thread thread1 = new Thread(
                () -> list.get(0).setName("rename1"));
        Thread thread2 = new Thread(
                () -> list.get(1).setName("rename2"));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat("name1", is(cache.findById(1).getName()));
        assertThat("name2", is(cache.findById(2).getName()));
    }
}