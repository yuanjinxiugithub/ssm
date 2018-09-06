package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.UserDao;
import com.ssm.domain.User;
import com.ssm.service.UserService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Transactional(readOnly = true)
@Service("userService")
public class UserServiceImpl implements UserService{
	 @Resource
	 private UserDao userDao;
	 
	 @Autowired 
	  private JedisPool jedisPool;
	 
	@Override
	public List<User> findList() {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		jedis.select(2);//切换redis数据库
		jedis.set("mykey","abc");
	    System.out.println("redis 存储的字符串为: "+ jedis.get("mykey"));
		return userDao.findList();
	}

}
