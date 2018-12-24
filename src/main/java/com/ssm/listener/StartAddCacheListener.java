package com.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class StartAddCacheListener implements ApplicationListener<ContextRefreshedEvent>
{
    final Logger logger  = LoggerFactory.getLogger(getClass());
     
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		logger.info("111");
		  if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
                     System.out.println("2222");
             		//userService.findList();
	   }
		  if (event instanceof ContextRefreshedEvent) { 
		/*	  System.out.println("spring容易初始化完毕================================================888"); */
			  } 
}
}
