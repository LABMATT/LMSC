package Mangers.SQL_Manger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class lmsc_SQL {

    public void DbConnect() {
        MongoClient mongoClient = MongoClients.create("mongodb://hostOne:27017,hostTwo:27018");

        MongoDatabase database = mongoClient.getDatabase("test");


    }

}
