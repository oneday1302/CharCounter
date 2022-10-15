package ua.foxminded.javaspring.charcounter;

import java.util.Map;
import java.util.StringJoiner;

public class Formatter {

    public String format(String value, Map<Character, Integer> map) {
        if (value == null || map == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add(value);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            result.add(String.format("\"%s\" - %d", entry.getKey(), entry.getValue()));
        }
        result.add("");
        return result.toString();
    }
}
