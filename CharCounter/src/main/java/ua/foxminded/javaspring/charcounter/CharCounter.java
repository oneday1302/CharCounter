package ua.foxminded.javaspring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter implements Counter {

    @Override
    public Map<Character, Integer> count(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        char[] valueCharArray = value.toCharArray();
        Map<Character, Integer> result = new LinkedHashMap<>();

        for (char c : valueCharArray) {
            if (result.containsKey(c)) {
                result.put(c, result.get(c) + 1);
            } else {
                result.put(c, 1);
            }
        }
        return result;
    }
}
