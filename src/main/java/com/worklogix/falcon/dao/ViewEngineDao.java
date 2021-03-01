package com.worklogix.falcon.dao;

import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.worklogix.falcon.model.Element;
import com.worklogix.falcon.model.Entity;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ViewEngineDao {

    private String database = "mongodb://192.168.1.34:27017";

    public String getView(String viewName) {

        String resultset = "";

        MongoClient mongoClient = MongoClients.create(database);

        // Get the metadata and store it in a model
        MongoDatabase database = mongoClient.getDatabase("metadata");
        MongoCollection<Document> collection = database.getCollection("entity");
        Bson filter = new Document("entity",viewName);
        Document doc = collection.find(filter).first();

        Entity entity = new Entity((String) doc.get("name"), (String) doc.get("description"));
        ArrayList<Document> elements = doc.get("elements", ArrayList.class);
        entity.setElements(elements);

        //Go get the properties and loop through them
        /*
        HashMap<String, Element> test = entity.getElements();
        for(Map.Entry<String, Element> elem:test.entrySet()) {
            System.out.println(elem.getKey());
            if (elem.getKey().equals("Area_Code")) {
                System.out.println(elem.getValue().getProperty("description"));
            }
        }*/

        // get the data and make changes to the columns.  We can then start with formatting.

        database = mongoClient.getDatabase("staging");
        collection = database.getCollection(viewName);

        StringBuilder items = new StringBuilder();
        MongoCursor<Document> cursor = collection.find().limit(100).iterator();
        while (cursor.hasNext()) {
            String str = cursor.next().toJson();

            str = str.replace("{\"$oid\":","");
            str = str.replace("},",",");

            //TODO: We need to reassemble each document so we can modify each field
            HashMap<String, Element> test = entity.getElements();
            for(Map.Entry<String, Element> elem:test.entrySet()) {
                //System.out.println(elem.getKey());
                str = str.replace(elem.getKey(),elem.getValue().getProperty("name"));
            }

            items.append(str);
            if (cursor.hasNext()) {
                items.append(",");
            }
        }

        resultset = "[" + items.toString() + "]";


        /*
        JSONObject idObj = (JSONObject)obj.get("_id");
        String strID = (String) idObj.get("$oid");


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

        mongoClient.close();
        return resultset;
    }
}
