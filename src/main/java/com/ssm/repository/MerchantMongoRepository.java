package com.ssm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ssm.domain.Test;

public interface MerchantMongoRepository extends MongoRepository<Test, String>{
  public  Test findById(String id); //根据id 获取po
  public  Page<Test> findAll(Pageable pageable);//获取所有数据，带分页排序
}
