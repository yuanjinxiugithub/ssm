package com.ssm.dao;

import java.util.List;

import com.ssm.domain.User;

public interface  UserDao {
	public abstract List<User> findList(); //获取所有的数据
}  
