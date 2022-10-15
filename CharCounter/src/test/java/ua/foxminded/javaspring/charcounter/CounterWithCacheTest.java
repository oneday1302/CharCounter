package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CounterWithCacheTest {

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CounterWithCache counterWithCache = new CounterWithCache(null, 1);
        });
    }

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputSecondParamLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            CounterWithCache counterWithCache = new CounterWithCache(new CharCounter(), -1);
        });
    }

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputSecondParamZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            CounterWithCache counterWithCache = new CounterWithCache(new CharCounter(), 0);
        });
    }

    @Test
    void count_shouldReturnEmptyMap_whenInputEmptyString() {
        CounterWithCache counterWithCache = new CounterWithCache(new CharCounter(), 3);
        Map<Character, Integer> expected = counterWithCache.count("");
        Map<Character, Integer> actual = new LinkedHashMap<>();

        assertEquals(actual, expected);
    }

    @Test
    void count_shouldReturnMapWithOneSymbol_whenInputOneSymbol() {
        CounterWithCache counterWithCache = new CounterWithCache(new CharCounter(), 3);
        Map<Character, Integer> expected = counterWithCache.count("!");
        Map<Character, Integer> actual = new LinkedHashMap<>();
        actual.put('!', 1);

        assertEquals(actual, expected);
    }

    @Test
    void count_shouldReturntThreeSymbol_whenInputThreeSymbol() {
        CounterWithCache counterWithCache = new CounterWithCache(new CharCounter(), 3);
        Map<Character, Integer> expected = counterWithCache.count("!!!");
        Map<Character, Integer> actual = new LinkedHashMap<>();
        actual.put('!', 3);

        assertEquals(actual, expected);
    }

    @Test
    void count_shouldReturnMap_whenInputNormalString() {
        CounterWithCache counterWithCache = new CounterWithCache(new CharCounter(), 3);
        Map<Character, Integer> expected = counterWithCache.count("hello word!");
        Map<Character, Integer> actual = new LinkedHashMap<>();
        actual.put('h', 1);
        actual.put('e', 1);
        actual.put('l', 2);
        actual.put('o', 2);
        actual.put(' ', 1);
        actual.put('w', 1);
        actual.put('r', 1);
        actual.put('d', 1);
        actual.put('!', 1);

        assertEquals(actual, expected);
    }
}
