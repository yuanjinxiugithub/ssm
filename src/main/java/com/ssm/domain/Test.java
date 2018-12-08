package com.ssm.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="test")
public class Test {
    String id;
    String name;
    String date;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
