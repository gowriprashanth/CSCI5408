package org.example;
import java.util.HashMap;
import java.util.Map;

public class Mapper {
    public void map(String line, Map<String, Integer> output) {
        String[] words = line.trim().split("\\s+");
        for (String word : words) {
            output.put(word, output.getOrDefault(word, 0) + 1);
        }
    }
}
