package com.ssm.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssm.service.UserService;
import com.ssm.service.impl.UserServiceImpl;

public class ContextLoaderListenerOverWrite extends ContextLoaderListener {
	
	 
	     @Override  
	     /** 
	     * @description 重写ContextLoaderListener的contextInitialized方法 
	     */  
	    public void contextInitialized(ServletContextEvent event) {  
	        super.contextInitialized(event);  
	        WebApplicationContext springContext =  WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()); 
	        
	       // UserServiceImpl sysServices = (UserServiceImpl)springContext.getBean("userService");  

	      //  System.out.println(sysServices.findList().size());
	        //获取bean  
	     //   osCache = (IOSCache) applicationContext.getBean("osCache");  
	        /* 
	         具体地业务代码 
	         */  
	    }  
}
