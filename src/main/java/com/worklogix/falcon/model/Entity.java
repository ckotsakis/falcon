package com.worklogix.falcon.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {

    private String entity;
    private String description;
    private HashMap<String, Element> elements;

    public Entity(String entity, String description) {
        this.entity = entity;
        this.description = description;
        elements = new HashMap<>();
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Document> elements) {

        for (Document doc:elements) {
            Element elem = new Element(doc);
            this.elements.put((String) doc.get("id"),elem);
        }

    }
}
