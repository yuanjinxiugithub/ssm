package com.ssm.service.log;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.ssm.base.util.SpringContextUtil;
import com.ssm.domain.LogInfo;

/**
 * @author yjx
 *   执行队列
 */
public class Queue {
	
	
   /* @Autowired
	private static  LogService logService;*/
	
	protected static Logger logger = LoggerFactory.getLogger(Queue.class);
	protected static ConcurrentLinkedQueue<LogInfo> queue = new ConcurrentLinkedQueue<>();//高并发的 基于链表的队列
	static {
        new Thread(){//匿名内部类
            @Override
            public void run() {
                while (true) {
                    LogInfo entity = queue.poll();//
                    if(entity != null){
                        try {
                            /*//WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                            ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
                            LogService logService =(LogService) appCtx.getBean("logService");*/

                          ApplicationContext appCtx = SpringContextUtil.getApplicationContext();
                        //  LogService logService = appCtx.getBean(LogService.class);
                          LogService logService =(LogService) appCtx.getBean("logService");
                          System.out.println("i am queue");
                            logService.insert(entity);
                            //logService.findList();
                        } catch (Exception e) {
                            // TODO: handle exception
                            logger.error(e.getMessage(), e);
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
