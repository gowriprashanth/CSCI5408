package com.dmwa;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
    public void parseAndStoreNews(MongoDatabase database, String collectionName, String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            String content = new String(Files.readAllBytes(filePath));

            // Split the content by <REUTERS> tags
            String[] newsArray = content.split("<REUTERS[^>]*>");

            // Process each news article and store it as a document in the database
            for (String news : newsArray) {
                if (news.trim().isEmpty()) {
                    continue;
                }

                String title = parseTagContent(news, "TITLE");
                String text = parseTagContent(news, "TEXT");

                // Create and store the document in MongoDB
                Document newsDocument = new Document("title", title).append("text", text);
                database.getCollection(collectionName).insertOne(newsDocument);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String parseTagContent(String content, String tagName) {
        int startTagIndex = content.indexOf("<" + tagName + ">");
        int endTagIndex = content.indexOf("</" + tagName + ">");
        if (startTagIndex != -1 && endTagIndex != -1) {
            return content.substring(startTagIndex + tagName.length() + 2, endTagIndex).trim();
        }
        return "";
    }
}

