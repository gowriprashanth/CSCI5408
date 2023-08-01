package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitleTag {
    String filePath1 = "C:/Users/AVuser/Documents/Data/Assignment_3/positive-words.txt";
    String filePath2 = "C:/Users/AVuser/Documents/Data/Assignment_3/negative-words.txt";
    FileReader textToList = new FileReader();
    BOW bag=new BOW();
    List<String> negativeWords = textToList.readFileList(filePath2);
    List<String> positiveWords = textToList.readFileList(filePath1);

    public HashMap<String, Integer> tagger(Map<String,Integer> bow1){
        HashMap<String, Integer> taggedBow = new HashMap<>();
        int overall=0;
        for(Map.Entry<String, Integer> entry : bow1.entrySet()) {
            String word = entry.getKey();
            int score = 0;

            if (positiveWords.contains(word)) {
                score = 1;


            } else if (negativeWords.contains(word)) {
                score = -1;

            }

            taggedBow.put(word, score);
        }
            return taggedBow;
    }

}
