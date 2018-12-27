package com.ssm.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.domain.Student;


public class TestDaoImpl extends BaseDao{
  public  void getUserList() throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
      BaseDao.initConnection();// 获取连接
      String sql = "select id as id,name as name , yuwen as chinese,shuxue as math, yingyu as english from student";
      ResultSet set = BaseDao.executeSQL(sql, null);
      
      List<Map<String,Object>> list = new ArrayList<>();
      Field[] field = Student.class.getDeclaredFields();//获取所有已声明字段的field对象的数组。
      List<Student> list2 = new ArrayList<>();
      while(set.next()){
       Map<String, Object> map = new HashMap<>();
       map.put("name", set.getString("name"));
       map.put("id", set.getInt("id"));
       map.put("语文", set.getString("chinese"));
       map.put("数学", set.getString("math"));
       map.put("英语", set.getString("english"));
       list.add(map);
       Student student = new Student();
       for(int i=0;i<field.length;i++){
          String methodName = "set"+ field[i].getName().substring(0, 1).toUpperCase() + field[i].getName().substring(1);
          Method method =Student.class.getMethod(methodName,field[i].getType()); //Field.getType 声明了Fiel对象所表示字段的声明类型  Field.getName 声明Field对象的字段名称
          method.invoke(student,BaseDao.setFieldValueByResultSet(field[i]));
       }
          list2.add(student);
      }
      BaseDao.closeAllConnection();
      for(Map<String, Object> map:list){
          System.out.println(map);
      }
      for(Student stu:list2){
          System.out.println(stu);
      }
  }
  
  public static void main(String[] args) throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      TestDaoImpl impl = new TestDaoImpl();
      impl.getUserList();
  }
}
