package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class BOW {
    public Map<String, Integer> BagOfWords(String title) {
        Map<String, Integer> bagOfWords = new HashMap<>();
        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = wordPattern.matcher(title);

        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (bagOfWords.containsKey(word)) {
                // Word already exists, increment its count
                int count = bagOfWords.get(word);
                bagOfWords.put(word, count + 1);
            } else {
                // Word does not exist, default 1
                bagOfWords.put(word, 1);
            }
        }
        return bagOfWords;
    }
}
