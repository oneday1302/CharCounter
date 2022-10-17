package ua.foxminded.javaspring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private int cacheSize;
    private LinkedHashMap<String, CounterResult> map = new LinkedHashMap<String, CounterResult>(cacheSize, 0.75f,
            true) {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, CounterResult> eldest) {
            return size() > cacheSize;
        }
    };

    public LRUCache(int cacheSize) {
        if (cacheSize <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }
        this.cacheSize = cacheSize;
    }

    public void put(String key, CounterResult value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }

        map.put(key, value);
    }

    public CounterResult get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        return map.get(key);
    }

    public boolean containsKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        return map.containsKey(key);
    }
}
