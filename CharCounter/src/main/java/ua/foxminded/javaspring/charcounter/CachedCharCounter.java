package ua.foxminded.javaspring.charcounter;

public class CachedCharCounter implements Counter {

    private LRUCache<String, CounterResult> cache;

    public CachedCharCounter(Counter counter, int cacheSize) {
        if (counter == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        if (cacheSize <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }
        cache = new LRUCache<>(counter::count, cacheSize);
    }

    @Override
    public CounterResult count(String value) {
        return cache.lookup(value);
    }
}
