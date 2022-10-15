package ua.foxminded.javaspring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<String, Map<Character, Integer>> {

    private static final long serialVersionUID = 1L;

    private int size;

    public LRUCache(int size) {
        super(size, 0.75f, true);

        if (size <= 0) {
            throw new IllegalArgumentException("Size cannot be less than or equal to zero.");
        }

        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<String, Map<Character, Integer>> eldest) {
        return size() > size;
    }
}
