package ru.job4j.synchronization;

import java.util.Objects;

/**
 * 3.1.3. Синхронизация ресурсов
 * 3. Класс хранилища пользователей UserStorage [#1104]
 * User модель данных.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 25.02.2022
 */
public final class User {
    private final int id;
    private final int amount;

    public User(final int id, final int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
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
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
