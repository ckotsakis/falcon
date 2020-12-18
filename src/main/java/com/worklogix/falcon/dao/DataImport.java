package com.worklogix.falcon.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

@Repository("mongoDao")
public class DataImport implements DataDao {
    private String database = "mongodb://192.168.1.34:27017";
    //private String collectionName = "mongodb://localhost:27017";

    @Override
    public void importData(String id, String fileName,String tableName, String desc, String techName) throws IOException {

        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        String[] columnNames;


        if((line = input.readLine()) != null) {
            columnNames = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            System.out.println(line + " (length = " + columnNames.length + ")");

            MongoClient mongoClient = MongoClients.create(database);
            MongoDatabase database = mongoClient.getDatabase("staging");

            //Save data to folders
            MongoCollection<Document> collection = database.getCollection("folder");
            Document doc = new Document(columnNames[0], tableName);
            doc.append("techname", techName);
            doc.append("description", desc);
            collection.insertOne(doc);

            // Load data into table
            collection = database.getCollection(techName);

            String[] row;

            while ((line = input.readLine()) != null) {
                doc = new Document();
                row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int col = 0; col < row.length; col++) {
                    doc.append(columnNames[col], row[col].replace("\"",""));
                }
                collection.insertOne(doc);

                System.out.println(line);
            }
            ProjectDao projectDao = new ProjectDao();
            projectDao.addData(id, tableName, desc, fileName, techName);
            mongoClient.close();
        }
    }

    @Override
    public String getData(String id) {

        String resultset = "";
        String tablename = "";

        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");

        //use the ID to find the table name
        MongoCollection<Document> projects = database.getCollection("projects");
        Document projdoc = projects.find(new Document("data.id",id)).first();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = (JSONObject) jsonParser.parse(projdoc.toJson());
            JSONArray data = (JSONArray) obj.get("data");

            Iterator objIter = data.iterator();
            int i = 0;
            while (objIter.hasNext()) {
                JSONObject jso = (JSONObject) objIter.next();
                String str = (String) jso.get("id");
                if(str.contains(id)) {
                    break;
                }
                i++;
            }
            JSONObject col = (JSONObject) data.get(i);
            tablename = (String) col.get("techname");

        } catch (ParseException parseException) {
            System.out.println(parseException.getMessage());
        }

        MongoCollection<Document> collection = database.getCollection(tablename);

        if (id.length() > 0) {
            //Document myDoc = collection.find();
            StringBuilder items = new StringBuilder();

            MongoCursor<Document> cursor = collection.find().iterator();
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
            //resultset = items.toString();

        }

        mongoClient.close();
        //System.out.println(resultset);

        return resultset;

    }

    @Override
    public void deleteData(String tableName) {
        MongoClient mongoClient = MongoClients.create(database);
        MongoDatabase database = mongoClient.getDatabase("staging");
        MongoCollection<Document> collection = database.getCollection(tableName);

        collection.drop();
        mongoClient.close();
    }

}
