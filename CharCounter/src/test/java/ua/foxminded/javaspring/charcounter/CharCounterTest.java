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
        Map<Character, Integer> expected = charCounter.count("");
        Map<Character, Integer> actual = new LinkedHashMap<>();

        assertEquals(actual, expected);
    }

    @Test
    void count_shouldReturnMapWithOneSymbol_whenInputOneSymbol() {
        CharCounter charCounter = new CharCounter();
        Map<Character, Integer> expected = charCounter.count("!");
        Map<Character, Integer> actual = new LinkedHashMap<>();
        actual.put('!', 1);

        assertEquals(actual, expected);
    }

    @Test
    void count_shouldReturntThreeSymbol_whenInputThreeSymbol() {
        CharCounter charCounter = new CharCounter();
        Map<Character, Integer> expected = charCounter.count("!!!");
        Map<Character, Integer> actual = new LinkedHashMap<>();
        actual.put('!', 3);

        assertEquals(actual, expected);
    }

    @Test
    void count_shouldReturnMap_whenInputNormalString() {
        CharCounter charCounter = new CharCounter();
        Map<Character, Integer> expected = charCounter.count("hello word!");
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
