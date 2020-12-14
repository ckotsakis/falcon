package com.worklogix.falcon.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Repository
public class ProjectDao {

    private String database = "mongodb://192.168.1.34:27017";

    public void createProject(String projectName,String projectDescription) throws IOException {
        String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        //Save data to folders
        MongoCollection<Document> collection = database.getCollection("projects");
        Document doc = new Document("name", projectName);
        doc.append("description", projectDescription);
        doc.append("createdon", today);
        doc.append("data", "");

        collection.insertOne(doc);

        System.out.println("Inserted");
        mongoClient.close();

    }

    public void addData(String name, String description){
        final String uuid = UUID.randomUUID().toString();
        String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

    }

}
