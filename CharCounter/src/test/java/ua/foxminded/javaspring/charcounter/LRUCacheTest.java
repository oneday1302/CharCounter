package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LRUCacheTest {

    @Test
    void LRUCache_shouldReturnIllegalArgumentException_whenInputSizeThanZero() {
        Counter mockCounter = Mockito.mock(Counter.class);
        assertThrows(IllegalArgumentException.class, () -> {
            LRUCache<String, CounterResult> cache = new LRUCache<>(mockCounter::count, -1);
        });
    }

    @Test
    void LRUCache_shouldReturnIllegalArgumentException_whenInputSizeZero() {
        Counter mockCounter = Mockito.mock(Counter.class);
        assertThrows(IllegalArgumentException.class, () -> {
            LRUCache<String, CounterResult> cache = new LRUCache<>(mockCounter::count, 0);
        });
    }

    @Test
    void LRUCache_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            LRUCache<String, CounterResult> cache = new LRUCache<>(null, 1);
        });
    }

    @Test
    void lookup_shouldReturnCounterResult_whenInputNormal() {
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
        CounterResult expected = new CounterResult("hello world!", map);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(expected);

        LRUCache<String, CounterResult> cache = new LRUCache<>(mockCounter::count, 1);
        cache.lookup("hello world!");
        CounterResult actual = cache.lookup("hello world!");

        assertEquals(expected, actual);
    }

    @Test
    void lookup_shouldReturnSavesValue_whenInputSeveralString() {
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

        CounterResult result = new CounterResult("hello world!", map);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(result);

        LRUCache<String, CounterResult> cache = new LRUCache<>(mockCounter::count, 2);
        cache.lookup("hello world!");
        cache.lookup("hello world!");

        verify(mockCounter, times(1)).count("hello world!");
    }

    @Test
    void lookup_shouldReturnInvalidatesEldestEntry_whenInputSeveralString() {
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

        Map<Character, Integer> mapTest = new LinkedHashMap<>();
        mapTest.put('t', 2);
        mapTest.put('e', 1);
        mapTest.put('s', 1);

        CounterResult resultHelloWorld = new CounterResult("hello world!", mapHelloWorld);
        CounterResult resultMyNameIsVlad = new CounterResult("My name is Vlad!", mapMyNameIsVlad);
        CounterResult resultTest = new CounterResult("test!", mapTest);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(resultHelloWorld);
        when(mockCounter.count("My name is Vlad!")).thenReturn(resultMyNameIsVlad);
        when(mockCounter.count("test")).thenReturn(resultTest);

        LRUCache<String, CounterResult> cache = new LRUCache<>(mockCounter::count, 2);
        cache.lookup("hello world!");
        cache.lookup("My name is Vlad!");
        cache.lookup("test");
        cache.lookup("hello world!");

        verify(mockCounter, times(2)).count("hello world!");
    }
}
