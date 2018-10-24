package com.ssm.service.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssm.domain.LogInfo;

@Component //定义类为spring 组件
@Aspect //定义类为切面类
public class LogAspect {
	@Autowired
	private LogService logDao;
	protected Logger logger = LoggerFactory.getLogger(getClass());
   
	/**   
	 * @Title: onMethod    方法执行时--定义切入点，提供一个方法,这个方法的名字就是该切入点的ID
	 * @Description: TODO(这里用一句话描述这个方法的作用)   这里使用的是 LogTest注解为切入点 
	 * @param:       
	 * @return: void      
	 * @throws   
	 */  
	@Pointcut("@annotation(com.ssm.service.log.LogTest)")
	public void onMethod(){
		System.out.println("pointcut");
	}
	
	/**  
	 * 前置通知 用于拦截Controller层 获取记录的操作信息  
	 *  
	 * @param joinPoint 切点  
	 */    
	@Before("onMethod()")    
	public void doBefore(JoinPoint joinPoint) {    
		System.out.println("before--");
	}
	
	
	/**  
	 * 后置通知 在某个拦截点正常完成之后执行的通知 
	 *  
	 * @param joinPoint 切点  
	 */    
	@After("onMethod()")    
	public void onAfter(JoinPoint joinPoint) {    
		System.out.println("after");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //通过反射获取执行的类名
		Method method = methodSignature.getMethod();//获取目标队形正在执行的方法
		if(method !=null){
			LogTest logA = method.getAnnotation(LogTest.class);
			LogInfo info = new LogInfo();
			 Random random = new Random();  
			info.setId(String.valueOf(random.nextInt(41) + 10));
		//	info.setBackInfo("123");
			info.setCreateTime(new Date());
			info.setLogType(String.valueOf(logA.getLogType()));
			/*logDao.insert(info);*/
			Queue.queue.offer(info);
		}
	}
	
	/**   
	 * @Title: around   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   方法周围通知。环绕通知可以在方法调用前后完成自定义的行为--procedd()方法执行才会执行目标方法
	 * @param: @param call
	 * @param: @return  
	 * @param: @throws Throwable      
	 * @return: Object    返回方法执行的数据
	 * @throws   
	 */  
	@Around(value = "onMethod()")
    public Object around(ProceedingJoinPoint call) throws Throwable{//ProceedingJoinPoint 获取当前正在请求的方法 获取参数的参数和名称 类似于springmvc的拦截器
		//这里是前置通知--在方法未执行前执行
		String methodName = call.getSignature().getName();//获取执行的方法
		long startTime=System.currentTimeMillis();
		LogTest log =getMethod(call,methodName); //获取方法上的注解
		Object proceed = null;
		proceed = call.proceed(); //让代理方法执行--就是通过代理执行目标方法
		System.out.println(proceed);
		long endTime=System.currentTimeMillis();
		long execTime = endTime-startTime;
		System.out.println(execTime);
		//这里将在方法执行之后再执行--后置通知
		System.out.println("插入日志记录");
		if(log !=null){
			System.out.println(log.getLogType());
		}
		return proceed;
	}
	
	/**   
	 * @Title: afterThrowing   
	 * @Description: TODO(这里用一句话描述这个方法的作用) 在方法抛出异常后的通知   
	 * @param: @param joinPoint
	 * @param: @param ex      
	 * @return: void      
	 * @throws   
	 */  
	@AfterThrowing(pointcut = "onMethod()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
	System.out.println("error");
	}
	/**   
	 * @Title: getMethod   获取方法名称 --通过反射机制获取连接点的 类--获取注解
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static LogTest getMethod(JoinPoint joinPoint, String methodName){
		Method[] methods = joinPoint.getTarget().getClass().getMethods();//根据反射获取目标对象对应的类名
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {//只返回有注解的方法
				return methods[i].getAnnotation(LogTest.class);
			}
		}
		return null;
	}
}
