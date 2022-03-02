package ru.job4j.nonblockingalgoritm.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 3.1.5. Non Blocking Algorithm
 * 1. Неблокирующий кеш [#4741]
 * Cache реализует кэш данных.
 * Задание.
 * 1. Реализуйте методы update, delete.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
@ThreadSafe
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    /**
     * Атомарное добавление объекта в карту.
     *
     * @param model Base.
     * @return Boolean.
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * Обновление объекта в карте.
     * Если версия значений совпадает,
     * то значение обновляется, и версия увеличивается на 1.
     *
     * @param model Base.
     * @return Boolean.
     */
    public boolean update(Base model) {
        return model.equals(memory.computeIfPresent(model.getId(),
                (k, v) -> {
                    if (v.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    Base result = new Base(v.getId(), v.getVersion() + 1);
                    result.setName(model.getName());
                    return result;
                }));
    }

    /**
     * Удаляет значение из cache.
     *
     * @param model Base.
     */
    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    /**
     * Возвращает значение из cache.
     *
     * @param key Integer.
     * @return Base.
     */
    public Base get(Integer key) {
        return memory.get(key);
    }
}

