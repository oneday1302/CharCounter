package ua.foxminded.javaspring.charcounter;

import java.util.Map;
import java.util.Objects;

public class CounterResult {

    private final String title;
    private final Map<Character, Integer> map;

    public CounterResult(String value, Map<Character, Integer> map) {
        if (value == null || map == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }

        this.title = value;
        this.map = map;
    }

    public String getTitle() {
        return title;
    }

    public Map<Character, Integer> getMap() {
        return map;
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CounterResult other = (CounterResult) obj;
        return Objects.equals(map, other.map) && Objects.equals(title, other.title);
    }
}
