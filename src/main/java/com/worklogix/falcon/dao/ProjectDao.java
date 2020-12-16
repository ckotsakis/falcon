package com.worklogix.falcon.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

@Repository
public class ProjectDao {

    private String database = "mongodb://192.168.1.34:27017";
    DataImport dataImport = new DataImport();

    public void createProject(String projectName,String projectDescription) throws IOException {
        final String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        //Save data to folders
        MongoCollection<Document> collection = database.getCollection("projects");
        Document doc = new Document("name", projectName);
        doc.append("description", projectDescription);
        doc.append("createdon", today);
        doc.append("data", new Document());

        collection.insertOne(doc);

        System.out.println("Inserted");
        mongoClient.close();

    }

    public String getProjects(String id) {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        MongoCollection<Document> collection = database.getCollection("projects");

        String resultset = "";
        StringBuilder items = new StringBuilder();

        MongoCursor<Document> cursor = collection.find().projection(Projections.exclude("data")).iterator();

        while (cursor.hasNext()) {
            items.append(cursor.next().toJson());
            if (cursor.hasNext()) {
                items.append(",");
            }
        }

        resultset = "[" + items.toString() + "]";
        System.out.println(resultset);
        mongoClient.close();
        return resultset;
    }

    public void addData(String id, String name, String description, String fileName) throws IOException{
        final String uuid = UUID.randomUUID().toString();
        final String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        MongoCollection<Document> collection = database.getCollection("projects");

        Bson filter = new Document("_id", new ObjectId(id));

        //uuid, name, description, fileName

        //String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"fileName\":\"%s\",\"today\":\"%s\"}",uuid,name,description,fileName, today);

        Document doc = new Document("id", uuid);
        doc.append("name", name);
        doc.append("description", description);
        doc.append("filename", fileName);
        doc.append("uploaded",today);

        //Bson newValue = new Document("data", doc);

        //Bson updateOperationDocument = new Document("$set", newValue);
        collection.updateOne(filter, Updates.addToSet("data", doc));
        mongoClient.close();
    }

    public void removeData(String id, String uuid){
        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        MongoCollection<Document> collection = database.getCollection("projects");

        Bson filter = new Document("_id", new ObjectId(id));

        ArrayList<?> doc = collection.find(filter).first().get("data", ArrayList.class);
        Document delete;

        for (Object o : doc) {
            if (o.toString().contains(uuid)) {
                delete = (Document) o;
                collection.updateOne(filter, Updates.pull("data", delete));
                String name = (String) collection.find(filter).first().get("name");
                dataImport.deleteData(name);
                break;
            }
        }



    }

}
