package ua.foxminded.javaspring.charcounter;

import java.util.Map;
import java.util.StringJoiner;

public class Formatter {

    public String format(CounterResult counterResult) {
        if (counterResult == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }

        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add(counterResult.getTitle());
        for (Map.Entry<Character, Integer> entry : counterResult.getMap().entrySet()) {
            result.add(String.format("\"%c\" - %d", entry.getKey(), entry.getValue()));
        }
        result.add("");
        return result.toString();
    }
}
