package com.ssm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ssm.domain.Test;
@Repository
public class MongoDao {
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    public List<Test> getAll(){
        return mongoTemplate.findAll(Test.class);
    }
    
}
