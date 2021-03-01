package com.worklogix.falcon.model;

import org.bson.Document;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.HashMap;
import java.util.Map;

public class Element {

    private String id;
    private HashMap<String, String> properties;

    public Element(Document document) {
        this.id = (String) document.get("id");
        properties = new HashMap<>();
        setProperties(document);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public String getProperty(String property) {
        return properties.get(property);
    }

    public void setProperties(Document document) {

        String str = document.toJson();
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(str);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            properties.put(entry.getKey(),(String) entry.getValue());
        }


    }
}
