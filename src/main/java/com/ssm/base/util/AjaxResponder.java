package com.ssm.base.util;

import java.io.Serializable;

/** Ajax调用时响应的统一对象
 * @author yjx
 *
 */
public class AjaxResponder implements Serializable{
	private static final long serialVersionUID = 9122592962950703984L;

	/**
	 * 业务状态，true-成功，false-失败，默认为false
	 */
	private Boolean succ = false;
	
	/**
	 * 提示信息，服务单需要输出到页面进行提示的文字信息，一般在失败时做提示使用
	 */
	private String msg = "未进行取值设置！"; 

	/**
	 * 响应的业务数据，一般为JSON的字符串
	 */
	private Object data = null;
	
	/**
	 * <p>Title: AjaxResponder</p> 
	 * <p>Description: 私有构造器</p>  
	 *
	 */
	private AjaxResponder(){}
	
	/**
	* <p>Title: getErrorInstance</p> 
	* <p>Description: 根据错误提示信息构建响应对象 </p>
	* @param msg 提示信息
	* @return
	 */
	public static AjaxResponder getErrorInstance(String msg){
		AjaxResponder ar = new AjaxResponder();
		ar.setSucc(Boolean.FALSE);
		ar.setMsg(msg);
		return ar;
	}
	
	/**
	* <p>Title: getSuccInstance</p> 
	* <p>Description:  根据响应数据构建成功响应对象</p>
	* @param data 业务数据
	* @return
	 */
	public static AjaxResponder getSuccInstance(Object data){
		AjaxResponder ar = new AjaxResponder();
		ar.setSucc(Boolean.TRUE);
		ar.setData(data);
		return ar;
	}
	
	/**
	* <p>Title: getInstance</p> 
	* <p>Description: 交由调用者决定的构造响应对象</p>
	* @param succ 处理状态
	* @param msg 提示信息
	* @param data 业务数据
	* @return
	 */
	public static AjaxResponder getInstance(Boolean succ , String msg , Object data){
		AjaxResponder ar = new AjaxResponder();
		ar.setSucc(succ);
		ar.setMsg(msg);
		ar.setData(data);
		return ar;
	}

	public Boolean getSucc() {
		return succ;
	}

	protected void setSucc(Boolean succ) {
		this.succ = succ;
	}

	public String getMsg() {
		return msg;
	}

	protected void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	protected void setData(Object data) {
		this.data = data;
	}

}
