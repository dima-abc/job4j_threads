package ru.job4j.nonblockingalgoritm.cache;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 3.1.5. Non Blocking Algorithm
 * 1. Неблокирующий кеш [#4741]
 * Test. Cache.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 02.03.2022
 */
public class CacheTest {
    private Cache cache;

    @Before
    public void init() {
        cache = new Cache();
    }

    @Test
    public void whenAddBase1AddBase2ThenTrue() {
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        assertTrue(cache.add(base2));
    }

    @Test
    public void whenAddBaseTrueAddBaseThenFalse() {
        Base base = new Base(1, 1);
        cache.add(base);
        assertFalse(cache.add(base));
    }

    @Test
    public void whenAddBaseGetKeyThenBase() {
        Base base = new Base(1, 1);
        cache.add(base);
        assertEquals(base, cache.get(base.getId()));
    }

    @Test
    public void whenUpdateBaseThenTrue() {
        Base base = new Base(1, 1);
        base.setName("one");
        cache.add(base);
        base.setName("versionTwo");
        assertTrue(cache.update(base));
    }

    @Test
    public void whenUpdateBaseThenFalse() {
        Base base = new Base(1, 1);
        base.setName("one");
        assertFalse(cache.update(base));
    }

    @Test
    public void whenUpdateBaseVersion1GetBaseVersion2() {
        Base base = new Base(1, 1);
        base.setName("one");
        cache.add(base);
        base.setName("versionTwo");
        cache.update(base);
        int expected = base.getVersion() + 1;
        assertThat(expected, is(cache.get(1).getVersion()));
    }

    @Test(expected = OptimisticException.class)
    public void whenBaseVersion1UpdateBaseVersion2ThenException() {
        Base base = new Base(1, 1);
        base.setName("version1");
        Base base1 = new Base(1, 2);
        base1.setName("version2");
        cache.add(base);
        cache.update(base1);
    }

    @Test
    public void whenAddBaseDeleteGetKeyThenNull() {
        Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertNull(cache.get(base.getId()));
    }
}