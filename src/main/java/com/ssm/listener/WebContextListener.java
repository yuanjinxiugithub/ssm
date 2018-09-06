package com.ssm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssm.service.impl.UserServiceImpl;

public class WebContextListener implements ServletContextListener {
	//监听tomact
	/* @Resource
	 private UserService userService;*/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());   
		 UserServiceImpl sysServices = (UserServiceImpl)context.getBean("userService");  
		System.out.println(sysServices.findList().size());
		System.out.println("11111");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("destory tomcat");
	}

}
