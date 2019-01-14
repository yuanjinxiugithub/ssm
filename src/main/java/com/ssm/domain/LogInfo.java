package com.ssm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yjx 日志信息类
 */
public class LogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;
    private String id;
    private String backInfo;// 返回的信息
    private Date createTime;
    private String logType;// 日志类型

    @Override
    public String toString() {
        return "LogInfo [tableName=" + tableName + ", id=" + id + ", backInfo=" + backInfo + ", createTime="
                + createTime + ", logType=" + logType + "]";
    }

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

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
