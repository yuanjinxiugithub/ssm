package com.ssm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.domain.User;

@Repository
public interface  UserDao {
	public abstract List<User> findList(); //获取所有的数据
}  
