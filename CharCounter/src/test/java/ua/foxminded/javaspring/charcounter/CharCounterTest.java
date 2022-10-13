package ua.foxminded.javaspring.charcounter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;

class CharCounterTest {

    @Test
    void counter_shouldReturnIllegalArgumentException_whenInputNull() {
        CharCounter charCounter = new CharCounter();
        assertThrows(IllegalArgumentException.class, () -> {
            charCounter.counter(null);
        });
    }
    
    @Test
    void counter_shouldReturnEmptyString_whenInputEmptyString() {
        CharCounter charCounter = new CharCounter();
        assertEquals("", charCounter.counter(""));
    }
    
    @Test
    void counter_shouldReturnOneSymbol_whenInputOneSymbol() {
        CharCounter charCounter = new CharCounter();
        String expected = charCounter.counter("!");
        
        String actual = new StringJoiner(System.lineSeparator())
                .add("!")
                .add("\"!\" - 1")
                .add("")
                .toString();
        
        assertEquals(actual, expected);
    }
    
    @Test
    void counter_shouldReturntThreeSymbol_whenInputThreeSymbol() {
        CharCounter charCounter = new CharCounter();
        String expected = charCounter.counter("!!!");
        
        String actual = new StringJoiner(System.lineSeparator())
                .add("!!!")
                .add("\"!\" - 3")
                .add("")
                .toString();
        
        assertEquals(actual, expected);
    }
    
    @Test
    void counter_shouldReturnString_whenInputNormalString() {
        CharCounter charCounter = new CharCounter();
        String expected = charCounter.counter("hello word!");
        
        String actual = new StringJoiner(System.lineSeparator())
                .add("hello word!")
                .add("\"h\" - 1")
                .add("\"e\" - 1")
                .add("\"l\" - 2")
                .add("\"o\" - 2")
                .add("\" \" - 1")
                .add("\"w\" - 1")
                .add("\"r\" - 1")
                .add("\"d\" - 1")
                .add("\"!\" - 1")
                .add("")
                .toString();
               
        assertEquals(actual, expected);
    }
}
