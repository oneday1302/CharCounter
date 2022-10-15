package ua.foxminded.javaspring.charcounter;

import java.util.Map;

public interface Counter {
    Map<Character, Integer> count(String value);
}
