package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ssm.dao.MongoDao;
import com.ssm.domain.Test;
import com.ssm.service.MerchantMongoService;

@Service("merchatMongoService")
public class MerchatMongoServiceImpl implements MerchantMongoService{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoDao mongoDao;
    @Override
    public List<Test> getpiclist(int current, int rowCount, String sortid) {
        // TODO Auto-generated method stub
        return mongoDao.getAll();
    }

    @Override
    public int getpicturenum() {
        // TODO Auto-generated method stub
        return 0;
    }
  /*  @Autowired
    private MerchantMongoDao merchatMongoDao;

    @Override
    public List<User> getpiclist(int current, int rowCount, String sortid) {
        PageRequest pr;
        if("asc".equals(sortid))
            pr =new PageRequest(--current, rowCount,Direction.ASC,"id");
        else if("desc".equals(sortid))
            pr =new PageRequest(--current, rowCount,Direction.DESC,"id");
        else 
            pr =new PageRequest(--current, rowCount);
        // TODO Auto-generated method stub
        Page<User> pageList = merchatMongoDao.findAll(pr);
        return pageList.getContent();
    }

    @Override
    public int getpicturenum() {
        // TODO Auto-generated method stub
        return (int)merchatMongoDao.count();
    }
*/
}
