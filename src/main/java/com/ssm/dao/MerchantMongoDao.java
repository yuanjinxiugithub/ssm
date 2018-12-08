package com.ssm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ssm.domain.User;

@Repository
public interface MerchantMongoDao extends MongoRepository<User, String>{
  /*  public User findById(String id);*/  //根据id获取PO
    User findById(String id);
    Page<User> findAll(Pageable pageable);//获取所有数据，带分页排序
   /* Page<User> findByFilenameContaining(String filename,Pageable pageable);//根据文件名过滤，带分页排序
    List<User> findByFilenameContaining(String filename);//根据文件名过滤的结果总数
*/}
