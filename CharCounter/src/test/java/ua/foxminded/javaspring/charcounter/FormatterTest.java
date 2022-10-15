package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;

class FormatterTest {

    @Test
    void format_shouldReturnIllegalArgumentException_whenInputFirstParamNull() {
        Formatter formatter = new Formatter();
        Map<Character, Integer> map = new LinkedHashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.format(null, map);
        });
    }

    @Test
    void format_shouldReturnIllegalArgumentException_whenInputSecondParamNull() {
        Formatter formatter = new Formatter();
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.format("a", null);
        });
    }

    @Test
    void format_shouldReturnIllegalArgumentException_whenInputBothParamsNull() {
        Formatter formatter = new Formatter();
        assertThrows(IllegalArgumentException.class, () -> {
            formatter.format(null, null);
        });
    }

    @Test
    void format_shouldReturnString_whenInputNormal() {
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('h', 1);
        map.put('e', 1);
        map.put('l', 2);
        map.put('o', 2);
        map.put(' ', 1);
        map.put('w', 1);
        map.put('r', 1);
        map.put('d', 1);
        map.put('!', 1);

        Formatter formatter = new Formatter();
        String expected = formatter.format("hello word!", map);

        StringJoiner actual = new StringJoiner(System.lineSeparator());
        actual.add("hello word!");
        actual.add("\"h\" - 1");
        actual.add("\"e\" - 1");
        actual.add("\"l\" - 2");
        actual.add("\"o\" - 2");
        actual.add("\" \" - 1");
        actual.add("\"w\" - 1");
        actual.add("\"r\" - 1");
        actual.add("\"d\" - 1");
        actual.add("\"!\" - 1");
        actual.add("");

        assertEquals(actual.toString(), expected);
    }
}
