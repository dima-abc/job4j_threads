package ru.job4j.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 3.1.2. Общие ресурсы
 * 4. Thread без общих ресурсов [#267919]
 * Класс UserCache реализует коллекцию кэш модели User.
 * Задание.
 * 1. Программист решил расширить класс UserCache и добавил в него новый метод findAll.
 * 2. Исправьте ошибку в этом коде.
 * Ошибка в примере в том что метод findAll реализует поверхностное копирование, этого недостаточно.
 * Решение реализовать глубокое копирования путем создание новых элементов на основании старых.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 23.02.2022
 */
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    /**
     * Добавление модели в Cache.
     *
     * @param user User.
     */
    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    /**
     * Извлекает копию User по id.
     *
     * @param id ID.
     * @return User.
     */
    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    /**
     * Извлекает содержимое users, применяя глубокую копию.
     *
     * @return List
     */
    public List<User> findAll() {
        return users.values().stream()
                .map(u -> User.of(u.getName()))
                .collect(Collectors.toList());
    }
}
