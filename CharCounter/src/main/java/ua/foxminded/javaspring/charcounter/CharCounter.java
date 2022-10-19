package ua.foxminded.javaspring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter implements Counter {

    @Override
    public CounterResult count(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : value.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        return new CounterResult(value, map);
    }
}
