package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ssm.domain.Test;
import com.ssm.domain.User;
import com.ssm.repository.MerchantMongoRepository;
import com.ssm.service.MerchantMongoService;

@Service("merchatMongoService")
public class MerchatMongoServiceImpl implements MerchantMongoService {
    /*
     * @Autowired private MongoDao mongoDao;
     */
    
    @Autowired
    private MerchantMongoRepository merchantMongoDao;

    @Override
    public List<Test> getpiclist(int current, int rowCount, String sortid) {
        // TODO Auto-generated method stub
        PageRequest pr;
        if ("asc".equals(sortid))
            pr = new PageRequest(--current, rowCount, Direction.ASC, "id");
        else if ("desc".equals(sortid))
            pr = new PageRequest(--current, rowCount, Direction.DESC, "id");
        else
            pr = new PageRequest(--current, rowCount);
        Page<Test> page = merchantMongoDao.findAll(pr);
        return page.getContent();
    }

    @Override
    public User getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNums() {
        // TODO Auto-generated method stub
        return (int) merchantMongoDao.count();
    }

    @Override
    public void SaveorUpdatePicture(Test test) {
        // TODO Auto-generated method stub
        merchantMongoDao.insert(test);
    }
}
