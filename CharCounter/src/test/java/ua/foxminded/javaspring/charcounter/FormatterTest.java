package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;

class FormatterTest {

    @Test
    void format_shouldReturnIllegalArgumentException_whenInputNull() {
        Formatter formatter = new Formatter();
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.format(null);
        });
    }

    @Test
    void format_shouldReturnString_whenInputNormal() {
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('h', 1);
        map.put('e', 1);
        map.put('l', 3);
        map.put('o', 2);
        map.put(' ', 1);
        map.put('w', 1);
        map.put('r', 1);
        map.put('d', 1);
        map.put('!', 1);
        CounterResult counterResult = new CounterResult("hello world!", map);
        
        Formatter formatter = new Formatter();
        String actual = formatter.format(counterResult);

        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("hello world!");
        expected.add("\"h\" - 1");
        expected.add("\"e\" - 1");
        expected.add("\"l\" - 3");
        expected.add("\"o\" - 2");
        expected.add("\" \" - 1");
        expected.add("\"w\" - 1");
        expected.add("\"r\" - 1");
        expected.add("\"d\" - 1");
        expected.add("\"!\" - 1");
        expected.add("");

        assertEquals(expected.toString(), actual);
    }
}
