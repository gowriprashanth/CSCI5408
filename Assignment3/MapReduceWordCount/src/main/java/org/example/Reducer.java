package org.example;
import java.util.HashMap;
import java.util.Map;
public class Reducer {
    public Map<String, Integer> reduce(Map<String, Integer> intermediate) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : intermediate.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            result.put(word, count);
        }
        return result;
    }
}
