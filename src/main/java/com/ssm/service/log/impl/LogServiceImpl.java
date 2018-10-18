package com.ssm.service.log.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.domain.LogInfo;
import com.ssm.service.log.LogService;

@Transactional(readOnly = true)
@Service("logService")
public class LogServiceImpl implements LogService{

	@Override
	public void exec(LogInfo logType) {
		// TODO Auto-generated method stub
		System.out.println("日志service启动");
		System.out.println(logType.getTableName());
		//将日志保存到数据库中
	}

}
