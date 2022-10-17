package ua.foxminded.javaspring.charcounter;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    @Test
    void LRUCache_shouldReturnIllegalArgumentException_whenInputLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            LRUCache cache = new LRUCache(-1);
        });
    }

    @Test
    void LRUCache_shouldReturnIllegalArgumentException_whenInputZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            LRUCache cache = new LRUCache(0);
        });
    }

    @Test
    void put_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        LRUCache cache = new LRUCache(3);
        Map<Character, Integer> map = new LinkedHashMap<>();
        CounterResult counterResult = new CounterResult("", map);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.put(null, counterResult);
        });
    }

    @Test
    void put_shouldReturnIllegalArgumentException_whenInputSecondParamNull() {
        LRUCache cache = new LRUCache(3);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.put("", null);
        });
    }

    @Test
    void put_shouldReturnIllegalArgumentException_whenInputBothParamsNull() {
        LRUCache cache = new LRUCache(3);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.put(null, null);
        });
    }

    @Test
    void get_shouldReturnIllegalArgumentException_whenInputNull() {
        LRUCache cache = new LRUCache(3);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(null);
        });
    }

    @Test
    void containsKey_shouldReturnIllegalArgumentException_whenInputNull() {
        LRUCache cache = new LRUCache(3);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.containsKey(null);
        });
    }
}
