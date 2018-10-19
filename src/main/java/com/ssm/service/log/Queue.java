package com.ssm.service.log;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssm.base.util.SpringContextUtil;
import com.ssm.domain.LogInfo;
import com.ssm.service.UserService;

/**
 * @author yjx
 *   执行队列
 */
public class Queue {
/*	@Autowired
	private static LogService logService;*/
	
	protected static Logger logger = LoggerFactory.getLogger(Queue.class);
	protected static ConcurrentLinkedQueue<LogInfo> queue = new ConcurrentLinkedQueue<>();//高并发的 基于链表的队列
	static{
		new Thread(){//匿名内部类
			@Override
			public void run() {
				while (true) {
					LogInfo entity = queue.poll();//
					if(entity != null){
						try {
							//WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
							ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
							UserService logService =(UserService) appCtx.getBean("userService");
							//logService.insert(entity);
							logService.findList();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							logger.error(e.toString());
						}
					}else{
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
			   }
		}
	}.start();
}

}
