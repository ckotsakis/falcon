package com.worklogix.falcon.dao;

import org.springframework.stereotype.Repository;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository("mongoDao")
public class DataImport implements DataDao {

    @Override
    public void importData(String fileName,String tableName) throws IOException {

        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        String[] columnNames;

        if((line = input.readLine()) != null) {
            columnNames = line.split(",");
            System.out.println(line + " (length = " + columnNames.length + ")");

            //MongoClient mongoClient = MongoClients.create("mongodb://192.168.1.34:27017");
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("staging");
            MongoCollection<Document> collection = database.getCollection(tableName);
            String[] row;

            while ((line = input.readLine()) != null) {
                Document doc = new Document();
                row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int col = 0; col < row.length; col++) {
                    doc.append(columnNames[col], row[col]);
                }
                collection.insertOne(doc);

            /*
            Document doc = new Document(columnNames[0], snippet.getName());
            doc.append("description", snippet.getDescription());
            doc.append("category", snippet.getCategory());
            doc.append("snippet", snippet.getSnippet());
            doc.append("technology", snippet.getTechnology());
            collection.insertOne(doc);
            */

                System.out.println(line);
            }
        }
    }

}
