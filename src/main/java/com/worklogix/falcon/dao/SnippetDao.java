package com.worklogix.falcon.dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class SnippetDao {

    private String database = "mongodb://192.168.1.34:27017";
    //private String database = "mongodb://localhost:27017";

    public void createSnippet(String snippetName, String snippetDescription, String language, String category, String framework, String code) throws IOException {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("snippets");

        MongoCollection<Document> collection = database.getCollection("codesnippets");
        Document doc = new Document("name", snippetName);
        doc.append("description", snippetDescription);
        doc.append("language", language);
        doc.append("category", category);
        doc.append("framework", framework);
        doc.append("code", code);

        //BasicDBList dbl = new BasicDBList();
        //doc.append("data", dbl);

        collection.insertOne(doc);

        System.out.println("Inserted");
        mongoClient.close();

    }

    public String getSnippets() {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("snippets");

        MongoCollection<Document> collection = database.getCollection("codesnippets");

        String resultset = "";
        StringBuilder items = new StringBuilder();

        MongoCursor<Document> cursor = collection.find().iterator();

        //JSONObject idObj = (JSONObject)obj.get("_id");
        //String strID = (String) idObj.get("$oid");

        while (cursor.hasNext()) {
            String str = cursor.next().toJson();

            str = str.replace("{\"$oid\":","");
            str = str.replace("},",",");

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

    public String getSnippet(String id) {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("snippets");
        MongoCollection<Document> collection = database.getCollection("codesnippets");

        String resultset = "";
        StringBuilder items = new StringBuilder();

        Bson filter = new Document("_id", new ObjectId(id));

        MongoCursor<Document> cursor = collection.find(filter).iterator();

        while (cursor.hasNext()) {
            String str = cursor.next().toJson();

            str = str.replace("{\"$oid\":","");
            str = str.replace("},",",");

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

    public int updateSnippet(String id, String code) {

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("snippets");
        MongoCollection<Document> collection = database.getCollection("codesnippets");

        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id)); // (1)

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("code", code); // (2)

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument); // (3)

        collection.updateOne(query, updateObject); // (4)

        return 1;
    }

}
