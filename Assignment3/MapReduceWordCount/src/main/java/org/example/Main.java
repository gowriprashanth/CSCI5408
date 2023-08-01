package org.example;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://gowriprashanth06:psMkfpacUAoNWBBe@csci5408.r5wotfr.mongodb.net/"; // Replace with your MongoDB connection string
        String databaseName = "ReuterDb"; // Replace with your MongoDB database name
        String collectionName = "newsArticle1"; // Replace with your MongoDB collection name


        MongoDBConnector mongoDBConnector = new MongoDBConnector(connectionString, databaseName, collectionName);
        MongoCollection<Document> collection = mongoDBConnector.getCollection();

        Mapper mapper = new Mapper();
        Reducer reducer = new Reducer();
        Map<String, Integer> intermediate = new HashMap<>();

        for (Document document : collection.find()) {
            String line = document.getString("text"); // Assuming the text is stored in the "text" field
            mapper.map(line, intermediate);
        }

        Map<String, Integer> output = reducer.reduce(intermediate);

        for (Map.Entry<String, Integer> entry : output.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        mongoDBConnector.closeConnection();
    }
}