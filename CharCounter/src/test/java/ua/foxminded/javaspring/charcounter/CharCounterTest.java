package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CharCounterTest {

    @Test
    void count_shouldReturnIllegalArgumentException_whenInputNull() {
        CharCounter charCounter = new CharCounter();
        assertThrows(IllegalArgumentException.class, () -> {
            charCounter.count(null);
        });
    }

    @Test
    void count_shouldReturnEmptyMap_whenInputEmptyString() {
        CharCounter charCounter = new CharCounter();
        CounterResult actual = charCounter.count("");
        Map<Character, Integer> map = new LinkedHashMap<>();
        CounterResult expected = new CounterResult("", map);

        assertEquals(expected, actual);
    }

    @Test
    void count_shouldReturnMapWithOneSymbol_whenInputOneSymbol() {
        CharCounter charCounter = new CharCounter();
        CounterResult actual = charCounter.count("!");
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('!', 1);
        CounterResult expected = new CounterResult("!", map);

        assertEquals(expected, actual);
    }

    @Test
    void count_shouldReturntThreeSymbol_whenInputThreeSymbol() {
        CharCounter charCounter = new CharCounter();
        CounterResult actual = charCounter.count("!!!");
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('!', 3);
        CounterResult expected = new CounterResult("!!!", map);

        assertEquals(expected, actual);
    }

    @Test
    void count_shouldReturnCounterResult_whenInputNormalString() {
        CharCounter charCounter = new CharCounter();
        CounterResult actual = charCounter.count("hello world!");
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('h', 1);
        map.put('e', 1);
        map.put('l', 3);
        map.put('o', 2);
        map.put(' ', 1);
        map.put('w', 1);
        map.put('r', 1);
        map.put('d', 1);
        map.put('!', 1);
        CounterResult expected = new CounterResult("hello world!", map); 

        assertEquals(expected, actual);
    }
}
