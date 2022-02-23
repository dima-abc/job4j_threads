package ru.job4j.ref;

import java.util.Objects;

/**
 * 3.1.2. Общие ресурсы
 * 4. Thread без общих ресурсов [#267919]
 * Модель данных User.
 * Локальные переменные видны только своей нити.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 23.02.2022
 */
public class User {
    private int id;
    private String name;

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
