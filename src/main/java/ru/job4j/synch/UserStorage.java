package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 3.1.3. Синхронизация ресурсов
 * 3. Класс хранилища пользователей UserStorage [#1104]
 * UserStorage реализация блокирующего кеша.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 25.02.2022
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    public synchronized User findId(int id) {
        return users.get(id);
    }

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(),
                user) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(),
                user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User fromUser = users.get(fromId);
        User toUser = users.get(toId);
        boolean result = false;
        if (fromUser != null
                && toUser != null
                && fromUser.getAmount() >= amount) {
            update(new User(fromId, fromUser.getAmount() - amount));
            update(new User(toId, toUser.getAmount() + amount));
            result = true;
        }
        return result;
    }
}
