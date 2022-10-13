package ua.foxminded.javaspring.charcounter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class CharCounter {

    public String counter(String value) {
        char[] valueCharArray = value.toCharArray();
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();

        for (char c : valueCharArray) {
            if (map.containsKey("\"" + Character.toString(c) + "\"")) {
                map.put("\"" + Character.toString(c) + "\"", map.get("\"" + Character.toString(c) + "\"") + 1);
            } else {
                map.put("\"" + Character.toString(c) + "\"", 1);
            }
        }
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add(value);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(String.format("%s - %d", entry.getKey(), entry.getValue()));
        }
        result.add("");
        return result.toString();
    }
}
