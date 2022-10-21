package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        Counter mockCounter = Mockito.mock(Counter.class);
        assertThrows(IllegalArgumentException.class, () -> {
            CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, -1);
        });
    }

    @Test
    void CounterWithCache_shouldReturnIllegalArgumentException_whenInputSecondParamZero() {
        Counter mockCounter = Mockito.mock(Counter.class);
        assertThrows(IllegalArgumentException.class, () -> {
            CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 0);
        });
    }

    @Test
    void count_shouldReturnCounterResult_whenInputMoreTimeOneString() {
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
            assertSame(expected, actual);
        }
    }

    @Test
    void count_shouldReturnCounterResult_whenInputSeveralString() {
        Map<Character, Integer> mapHelloWorld = new LinkedHashMap<>();
        mapHelloWorld.put('h', 1);
        mapHelloWorld.put('e', 1);
        mapHelloWorld.put('l', 2);
        mapHelloWorld.put('o', 2);
        mapHelloWorld.put(' ', 1);
        mapHelloWorld.put('w', 1);
        mapHelloWorld.put('r', 1);
        mapHelloWorld.put('d', 1);
        mapHelloWorld.put('!', 1);

        Map<Character, Integer> mapMyNameIsVlad = new LinkedHashMap<>();
        mapMyNameIsVlad.put('M', 1);
        mapMyNameIsVlad.put('y', 1);
        mapMyNameIsVlad.put(' ', 3);
        mapMyNameIsVlad.put('n', 1);
        mapMyNameIsVlad.put('a', 2);
        mapMyNameIsVlad.put('m', 1);
        mapMyNameIsVlad.put('e', 1);
        mapMyNameIsVlad.put('i', 1);
        mapMyNameIsVlad.put('s', 1);
        mapMyNameIsVlad.put('V', 1);
        mapMyNameIsVlad.put('l', 1);
        mapMyNameIsVlad.put('d', 1);
        mapMyNameIsVlad.put('!', 1);

        Map<Character, Integer> mapHello = new LinkedHashMap<>();
        mapHello.put('H', 1);
        mapHello.put('e', 1);
        mapHello.put('l', 2);
        mapHello.put('o', 1);

        Map<Character, Integer> mapTest = new LinkedHashMap<>();
        mapTest.put('t', 2);
        mapTest.put('e', 1);
        mapTest.put('s', 1);

        CounterResult expectedHelloWorld = new CounterResult("hello world!", mapHelloWorld);
        CounterResult expectedMyNameIsVlad = new CounterResult("My name is Vlad!", mapMyNameIsVlad);
        CounterResult expectedHello = new CounterResult("Hello", mapHello);
        CounterResult expectedTest = new CounterResult("test", mapTest);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(expectedHelloWorld);
        when(mockCounter.count("My name is Vlad!")).thenReturn(expectedMyNameIsVlad);
        when(mockCounter.count("Hello")).thenReturn(expectedHello);
        when(mockCounter.count("test")).thenReturn(expectedTest);

        CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 3);
        CounterResult actualHelloWorld = cachedCharCounter.count("hello world!");
        assertEquals(expectedHelloWorld, actualHelloWorld);
        CounterResult actualMyNameIsVlad = cachedCharCounter.count("My name is Vlad!");
        assertEquals(expectedMyNameIsVlad, actualMyNameIsVlad);
        CounterResult actualHello = cachedCharCounter.count("Hello");
        assertEquals(expectedHello, actualHello);
        actualHelloWorld = cachedCharCounter.count("hello world!");
        assertEquals(expectedHelloWorld, actualHelloWorld);
        CounterResult actualTest = cachedCharCounter.count("test");
        assertEquals(expectedTest, actualTest);
    }

    @Test
    void count_shouldInvalidatesEldestEntry_whenInputSeveralString() {
        Map<Character, Integer> map = new LinkedHashMap<>();
        CounterResult result = new CounterResult("test", map);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(result);
        when(mockCounter.count("My name is Vlad!")).thenReturn(result);
        when(mockCounter.count("Hello")).thenReturn(result);
        when(mockCounter.count("test")).thenReturn(result);

        CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 2);
        cachedCharCounter.count("hello world!");
        cachedCharCounter.count("Hello");
        cachedCharCounter.count("Hello");
        cachedCharCounter.count("My name is Vlad!");
        cachedCharCounter.count("test");
        cachedCharCounter.count("hello world!");

        verify(mockCounter, times(1)).count("Hello");
        verify(mockCounter, times(2)).count("hello world!");
    }
    
    @Test
    void count_shouldSavesValuePassedViaPut_whenInputSeveralString() {
        Map<Character, Integer> map = new LinkedHashMap<>();
        CounterResult result = new CounterResult("test", map);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(result);

        CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 2);
        cachedCharCounter.count("hello world!");
        cachedCharCounter.count("hello world!");

        verify(mockCounter, times(1)).count("hello world!");
    }
}
