package com.ssm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yjx
 *  日志信息类
 */
public class LogInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String id;
	private Date crateTime;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCrateTime() {
		return crateTime;
	}
	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}
	

}
