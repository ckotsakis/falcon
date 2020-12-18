package com.worklogix.falcon.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        BasicDBList dbl = new BasicDBList();
        doc.append("data", dbl);

        collection.insertOne(doc);

        System.out.println("Inserted");
        mongoClient.close();

    }

    public String getProjects() {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        MongoCollection<Document> collection = database.getCollection("projects");

        String resultset = "";
        StringBuilder items = new StringBuilder();

        MongoCursor<Document> cursor = collection.find().projection(Projections.exclude("data")).iterator();

        //JSONObject idObj = (JSONObject)obj.get("_id");
        //String strID = (String) idObj.get("$oid");

        while (cursor.hasNext()) {
            String str = cursor.next().toJson();
            System.out.println(str);
            str = str.replace("{\"$oid\":","");
            str = str.replace("},",",");
            System.out.println(str);
/*
            JsonParser springParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = springParser.parseMap(str);
            String mapArray[] = new String[map.size()];
            //System.out.println("Items found: " + mapArray.length);

            int i = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
                i++;
            }
*/
            //db.projects.aggregate([{$project:{"_id":{$toString:"$_id"},"name":1,"description":1}}])


            items.append(str);
            if (cursor.hasNext()) {
                items.append(",");
            }
        }

        resultset = "[" + items.toString() + "]";
        //System.out.println(resultset);
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

    public String getData(String id) {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        MongoCollection<Document> collection = database.getCollection("projects");

        String resultset = "";
        StringBuilder items = new StringBuilder();

        Bson filter = new Document("_id", new ObjectId(id));

        if(collection.find(filter).first().get("data", ArrayList.class) != null) {
            ArrayList<?> doc = collection.find(filter).first().get("data", ArrayList.class);

            for (int i = 0; i < doc.size(); i++) {
                Document str = (Document) doc.get(i);
                items.append(str.toJson());
                if (i < (doc.size() - 1)) {
                    items.append(",");
                }
            }

            resultset = "[" + items.toString() + "]";
            System.out.println(resultset);
        } else {
            resultset = "No data";
        }
        mongoClient.close();
        return resultset;
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
