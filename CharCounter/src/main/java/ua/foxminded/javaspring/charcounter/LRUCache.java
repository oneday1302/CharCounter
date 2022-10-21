package ua.foxminded.javaspring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.function.Function;

public class LRUCache<K, V> {

    private LinkedHashMap<K, V> map;
    private Function<K, V> lookupFunction;

    public LRUCache(Function<K, V> lookupFunction, int cacheSize) {
        if (lookupFunction == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        if (cacheSize <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }
        this.lookupFunction = lookupFunction;
        map = new LinkedHashMap<K, V>(cacheSize, 0.75f, true) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
    }

    public V lookup(K kay) {
        if (kay == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return map.computeIfAbsent(kay, lookupFunction::apply);
    }
}
