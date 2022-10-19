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
        List<String> list = new ArrayList<>();
        list.add("hello world!");
        list.add("My name is Vlad!");
        list.add("Hello");
        list.add("hello world!");
        list.add("test");
        
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

        CounterResult expected1 = new CounterResult("hello world!", mapHelloWorld);
        CounterResult expected2 = new CounterResult("My name is Vlad!", mapMyNameIsVlad);
        CounterResult expected3 = new CounterResult("Hello", mapHello);
        CounterResult expected4 = new CounterResult("hello world!", mapHelloWorld);
        CounterResult expected5 = new CounterResult("test", mapTest);

        List<CounterResult> expecteds = new ArrayList<>();
        expecteds.add(expected1);
        expecteds.add(expected2);
        expecteds.add(expected3);
        expecteds.add(expected4);
        expecteds.add(expected5);

        Counter mockCounter = Mockito.mock(Counter.class);
        when(mockCounter.count("hello world!")).thenReturn(expected1);
        when(mockCounter.count("My name is Vlad!")).thenReturn(expected2);
        when(mockCounter.count("Hello")).thenReturn(expected3);
        when(mockCounter.count("hello world!")).thenReturn(expected4);
        when(mockCounter.count("test")).thenReturn(expected5);

        CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 3);
        for (int i = 0; i < list.size(); i++) {
            CounterResult actual = cachedCharCounter.count(list.get(i));
            assertEquals(expecteds.get(i), actual);
        }
    }

    @Test
    void count_shouldInvalidatesEldestEntry_whenInputSeveralString() {
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

        CachedCharCounter cachedCharCounter = new CachedCharCounter(mockCounter, 2);
        cachedCharCounter.count("hello world!");
        cachedCharCounter.count("My name is Vlad!");
        cachedCharCounter.count("test");
        cachedCharCounter.count("hello world!");

        verify(mockCounter, times(2)).count("hello world!");
    }
}
