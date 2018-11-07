package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.UserDao;
import com.ssm.domain.User;
import com.ssm.service.UserService;
import com.ssm.service.log.LogService;
import com.ssm.service.log.LogTest;
import com.ssm.service.log.LogType;

@Transactional(readOnly = true)
@Service("userService")
public class UserServiceImpl implements UserService{
	 @Resource
	 private UserDao userDao;
	 
	/* @Autowired 
	  private JedisPool jedisPool;*/
	 
	  @Autowired 
	  private LogService logService;
	 
    
	@Override
	@LogTest(getLogType = LogType.type1)
	@Transactional(readOnly = false)
	public List<User> findList() {
		/*Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.select(2);//切换redis数据库
			jedis.set("mykey", "abc");
			System.out.println("redis 存储的字符串为: "+ jedis.get("mykey"));
		} finally {
			// TODO: handle finally clause
			if(jedis != null){
				jedis.close();
				jedis.disconnect();
				jedis=null;
			}
		}*/
		//logService.exec(null);
		return userDao.findList();
		
	}

}
