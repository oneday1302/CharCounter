package ua.foxminded.javaspring.charcounter;

public class CachedCharCounter implements Counter {

    private Counter counter;
    private LRUCache cache;

    public CachedCharCounter(Counter counter, int cacheSize) {
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
    public CounterResult count(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        if (cache.containsKey(value)) {
            return cache.get(value);
        }

        CounterResult result = counter.count(value);
        cache.put(value, result);
        return result;
    }
}
