package ru.job4j.synch;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * 3.1.3. Синхронизация ресурсов
 * 3. Класс хранилища пользователей UserStorage [#1104]
 * Test. UserStorage.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 25.02.2022
 */
public class UserStorageTest {
    private UserStorage storage;

    @Before
    public void init() {
        storage = new UserStorage();
    }

    @Test
    public void whenAddUserThenTrue() {
        User user = new User(1, 100);
        assertTrue(storage.add(user));
        assertEquals(user, storage.findId(1));
    }

    @Test
    public void whenAddUserThenFalse() {
        User user = new User(1, 200);
        storage.add(user);
        assertFalse(storage.add(user));
    }

    @Test
    public void whenUpdateUserThenTrue() {
        User user = new User(1, 100);
        User user1 = new User(1, 300);
        storage.add(user);
        assertTrue(storage.update(user1));
        assertThat(user1, is(storage.findId(1)));
    }

    @Test
    public void whenUpdateUserThenFalse() {
        storage.add(new User(1, 500));
        assertFalse(storage.update(new User(2, 500)));
    }

    @Test
    public void whenDeleteUserThenTrue() {
        User user = new User(1, 300);
        storage.add(user);
        assertThat(user, is(storage.findId(1)));
        assertTrue(storage.delete(user));
        assertNull(storage.findId(1));
    }

    @Test
    public void whenDeleteUserThenFalse() {
        User user = new User(1, 300);
        assertFalse(storage.delete(user));
    }

    @Test
    public void whenTransferFromUser1ToUser2Of300ThenTrue() {
        User user1 = new User(1, 400);
        User user2 = new User(2, 0);
        storage.add(user1);
        storage.add(user2);
        assertTrue(storage.transfer(1, 2, 300));
        assertThat(100, is(storage.findId(1).getAmount()));
        assertThat(300, is(storage.findId(2).getAmount()));
    }
}