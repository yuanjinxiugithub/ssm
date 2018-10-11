package com.ssm.base.util;
/**
 * 作为项目开发过程中抛出运行时异常的基类存在，其他运行时异常继承自该类
 * @author Administrator
 * @date 2017年5月15日 下午4:48:59  
 */
public class BaseExceptionUtil extends RuntimeException{
	private static final long serialVersionUID = 8663528996723927975L;
	
	/**
	 * <p>Title: 构造器</p> 
	 * <p>Description: 带有程序异常与异常描述的构造</p> 
	 * @param message 异常描述
	 * @param cause 异常对象
	 *
	 */
	public BaseExceptionUtil(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * <p>Title: 构造器</p> 
	 * <p>Description:只带有异常描述的构造</p> 
	 * @param message 异常描述
	 *
	 */
	public BaseExceptionUtil(String message) {
		super(message);
	}


}
