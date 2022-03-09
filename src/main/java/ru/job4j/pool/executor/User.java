package ru.job4j.pool.executor;

import java.util.Objects;

/**
 * 3.1.6. Пулы
 * 2. ExecutorService рассылка почты.
 * Задача.  Реализовать сервис для рассылки почты.
 * User модель данных.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.03.2022
 */
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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
        return Objects.equals(username, user.username) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
