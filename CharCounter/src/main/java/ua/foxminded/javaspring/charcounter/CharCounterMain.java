package ua.foxminded.javaspring.charcounter;

import java.util.ArrayList;
import java.util.List;

public class CharCounterMain {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("hello world!");
        list.add("My name is Vlad!");
        list.add("Hello");
        list.add("hello world!");
        list.add("test");
        list.add("hello world!");

        Counter counter = new CharCounter();
        CachedCharCounter cachedCharCounter = new CachedCharCounter(counter::count, 3);
        Formatter formatter = new Formatter();

        for (String value : list) {
            CounterResult counterResult = cachedCharCounter.count(value);
            System.out.println(formatter.format(counterResult));
        }
    }
}
