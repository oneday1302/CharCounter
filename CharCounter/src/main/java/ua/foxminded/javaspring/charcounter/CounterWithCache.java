package ua.foxminded.javaspring.charcounter;

import java.util.Map;

public class CounterWithCache implements Counter {

    private Counter counter;
    private LRUCache cache;

    public CounterWithCache(Counter counter, int cacheSize) {
        if (counter == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        if (cacheSize <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }

        this.counter = counter;
        cache = new LRUCache(cacheSize);
    }

    @Override
    public Map<Character, Integer> count(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        
        if (cache.containsKey(value)) {
            return cache.get(value);
        }

        Map<Character, Integer> result = counter.count(value);
        cache.put(value, result);
        return result;
    }
}
