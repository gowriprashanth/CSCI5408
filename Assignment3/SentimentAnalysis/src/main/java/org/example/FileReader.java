package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readFileList(String filePath) {
            List<String> wordsList = new ArrayList<>();

            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                for (String line : lines) {
                    String word = line.trim();
                    wordsList.add(word);
                }
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }

            return wordsList;
    }

}
