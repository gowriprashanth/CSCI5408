package com.dmwa;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
public class ReutRead {

    public static void main(String[] args) {
        Parser parser = new Parser();
        String uri = "mongodb+srv://gowriprashanth06:psMkfpacUAoNWBBe@csci5408.r5wotfr.mongodb.net/";
        ServerApi newServerApi = ServerApi.builder().version(ServerApiVersion.V1).build();
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(uri)).serverApi(newServerApi).build();
        String databaseName = "ReuterDb";
        String collectionName1 = "newsArticle1";
        String collectionName2 = "newsArticle2";
        String newsFile1="C:/Users/AVuser/Documents/Data/Assignment_3/reut2-009.sgm";
        String newsFile2="C:/Users/AVuser/Documents/Data/Assignment_3/reut2-014.sgm";
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                System.out.println("You successfully connected to MongoDB!");
                MongoDatabase database = mongoClient.getDatabase(databaseName);
                parser.parseAndStoreNews(database, collectionName1,newsFile1 );
                parser.parseAndStoreNews(database, collectionName2,newsFile2 );
            } catch (MongoException me) {
                System.err.println(me);
            }
        }
    }

}
