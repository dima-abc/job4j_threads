package ru.job4j.synchronization;

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
        User result = null;
        if (users.get(id) != null) {
            result = new User(id, users.get(id).getAmount());
        }
        return result;
    }

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(),
                new User(user.getId(), user.getAmount())) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(),
                new User(user.getId(), user.getAmount())) != null;
    }

    public synchronized boolean delete(User user) {
        return user.equals(users.remove(user.getId()));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (users.containsKey(toId)
                && users.containsKey(fromId)
                && users.get(fromId).getAmount() >= amount) {
            int newAmountTo = users.get(toId).getAmount() + amount;
            int newAmountFrom = users.get(fromId).getAmount() - amount;
            update(new User(fromId, newAmountFrom));
            update(new User(toId, newAmountTo));
            result = true;
        }
        return result;
    }
}
