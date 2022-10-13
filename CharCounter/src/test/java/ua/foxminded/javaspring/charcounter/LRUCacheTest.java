package ua.foxminded.javaspring.charcounter;

import static org.junit.jupiter.api.Assertions.*;
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
    void get_shouldReturnIllegalArgumentException_whenInputNull() {
        LRUCache cache = new LRUCache(2);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.get(null);
        });
    }
    
    @Test
    void put_shouldReturnIllegalArgumentException_whenInputOneOfTheArgumentsNull() {
        LRUCache cache = new LRUCache(2);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.put(null, "a");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            cache.put("a", null);
        });
    }
    
    @Test
    void put_shouldReturnIllegalArgumentException_whenInputBothArguments() {
        LRUCache cache = new LRUCache(2);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.put(null, null);
        });
    }
    
    @Test
    void containsKey_shouldReturnIllegalArgumentException_whenInputNull() {
        LRUCache cache = new LRUCache(2);
        assertThrows(IllegalArgumentException.class, () -> {
            cache.containsKey(null);
        });
    }
}
