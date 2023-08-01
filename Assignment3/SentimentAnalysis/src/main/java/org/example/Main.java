package org.example;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TitleTag tag = new TitleTag();
        int count=1;
        BOW bag = new BOW();
        String uri = "mongodb+srv://gowriprashanth06:psMkfpacUAoNWBBe@csci5408.r5wotfr.mongodb.net/";
        String dbName = "ReuterDb";
        String collectionName = "newsArticle1";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        List<String> titleList = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String title = document.getString("title");
                String cleanedInput = title.replaceAll("&lt;", "<");
                titleList.add(cleanedInput);
            }
        }


        for (String title : titleList) {
            int overall=0;

            Map<String, Integer> bagOfWords = bag.BagOfWords(title);
            Map<String,Integer> sentiment = tag.tagger(bagOfWords);

            System.out.println("News "+count+"/Title: " + title);
            //System.out.println("Sentiment " + sentiment);
            List<String> match = new ArrayList<>();

            for(Map.Entry<String, Integer> entry : sentiment.entrySet()) {
                if(entry.getValue()==1||entry.getValue()==-1)
                    match.add(entry.getKey());
                overall+=entry.getValue();
            }

            System.out.println("match : "+match);
            if(overall>=1){
                System.out.println("Polarity : positive");
            }
            else if(overall==0){
                System.out.println("Polarity : neutral");
            }
            else
                System.out.println("Polarity : negative");

            System.out.println("Score : "+overall);
            System.out.println();
            count++;
        }


        mongoClient.close();
    }


}