package com.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.LogDao;
import com.ssm.domain.LogInfo;
import com.ssm.service.log.LogService;

@Transactional(readOnly = true)
@Service("logService")
public class LogServiceImpl implements LogService{
	@Autowired
	private LogDao logDao;

	
	@Override
	@Transactional(readOnly = false)
	public void insert(LogInfo logType) {
		// TODO Auto-generated method stub
		//synchronized (this) {//使用sychronized 关键字 上锁
			System.out.println("-------------------logService启动成功---------------");
			System.out.println(logType.getLogType());
			logDao.insert(logType);
			//将日志保存到数据库中
			System.out.println("-------------------logService日志结束---------------");
		//}
		
	}
}
