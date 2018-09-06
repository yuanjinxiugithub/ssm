package com.ssm.webservice.impl;

import javax.jws.WebService;

import com.ssm.webservice.HelloService;

@WebService
public class HelloServiceImpl implements HelloService{

	@Override
	public String testHello(String msgId) {
		// TODO Auto-generated method stub
		System.out.println("调用web service 成功");
		return "调用成功";
	}

}
