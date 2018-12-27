package com.ssm.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * @author yjx Dao 所有的数据相关的操作 --自定义的mysql-connector 连接数据库
 */
public class BaseDao {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/test";
    private static String user = "root";
    private static String pwd = "12345678";
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initConnection() throws SQLException { // 初始化connection
        conn = DriverManager.getConnection(url, user, pwd);
    }

    public static void closeAllConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static ResultSet executeSQL(String sql, Object[] param) {// 执行sql
        try {
            pstmt = conn.prepareStatement(sql); // 得到PreParedStatement 对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]);// 为预编译设置sql
                }
            }
            rs = pstmt.executeQuery(sql); // 执行sql 语句
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); // sql 异常处理
        }
        return rs;
    }
    
    /**   
     * @Title: setFieldValueByResultSet   
     * @Description: TODO  利用反射机制 将结果对应到字段上
     * @param: @param field
     * @param: @return
     * @param: @throws SQLException      
     * @return: Object      
     * @throws   
     */  
    public static Object setFieldValueByResultSet(Field field) throws SQLException{
        ResultSetMetaData meteData = rs.getMetaData();// 获取resultset 对象中的列的类型和属性信息的对象
        Object res = null;
        int cols = meteData.getColumnCount();
        for(int i=1;i<=cols;i++){
           if(field.getName().equals(meteData.getColumnLabel(i))){
               res = rs.getString(field.getName());
           }
        }
        return res;
    }

}
