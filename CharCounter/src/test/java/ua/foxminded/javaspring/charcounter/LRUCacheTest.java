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
}
