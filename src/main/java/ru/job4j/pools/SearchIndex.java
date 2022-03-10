package ru.job4j.pools;

/**
 * 3.1.6. Пулы
 * 3. ForkJoinPool
 * Пример. Поиск элемента в массиве рекурсивно и линейно.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 10.03.2022
 */
public class SearchIndex {
    public static int index = -1;
    public static int i = 0;

    public static int getIndex(Object[] objects, Object key) {
        for (int j = 0; j < objects.length; j++) {
            if (key.equals(objects[j])) {
                return j;
            }
        }
        return index;
    }

    public static int getIndexRecurs(Object[] objects, Object key) {
        if (objects.length == i) {
            return index;
        }
        if (key.equals(objects[i])) {
            index = i;
        }
        i++;
        return getIndexRecurs(objects, key);
    }
}
