package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CachedCharCounterTest {

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CachedCharCounter cachedCharCounter = new CachedCharCounter(null, 1);
        });
    }

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputSecondParamLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            CachedCharCounter cachedCharCounter = new CachedCharCounter(new CharCounter(), -1);
        });
    }

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputSecondParamZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            CachedCharCounter cachedCharCounter = new CachedCharCounter(new CharCounter(), 0);
        });
    }

    @Test
    void count_shouldReturnCounterResult_whenInputNormalString() {
        List<String> list = new ArrayList<>();
        list.add("hello world!");
        list.add("hello world!");
        list.add("hello world!");
        list.add("hello world!");
        list.add("hello world!");

        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('h', 1);
        map.put('e', 1);
        map.put('l', 2);
        map.put('o', 2);
        map.put(' ', 1);
        map.put('w', 1);
        map.put('r', 1);
        map.put('d', 1);
        map.put('!', 1);

        Counter mockCounter = Mockito.mock(Counter.class);
        CounterResult expected = new CounterResult("hello world!", map);
        when(mockCounter.count("hello world!")).thenReturn(expected);

        CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 3);
        for (String value : list) {
            CounterResult actual = cachedCharCounter.count(value);
            assertEquals(expected, actual);
        }
    }
}
