package ssm.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yjx
 *  测试for循环的效率
 */
public class TestFor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		for(int i = 0;i<100000;i++){
			list.add("1");
		}
		int size = list.size();
      long t1 = System.currentTimeMillis();//获取当前时间戳
      
      //普通的for循环
      for(int i =0 ;i<100000;i++){
    	  for(int j=0;j<size;j++){
    		  list.get(i);
    	  }
      }
      
      long t2 = System.currentTimeMillis();//获取当前时间戳
      //增强for循环
      for(int i =0 ;i<100000;i++){
    	  for(String str:list){
    		String str1 =str;
    	  }
      }
      
      long t3 = System.currentTimeMillis();//获取当前时间戳
      //迭代器
      for(int i =0 ;i<100000;i++){
    	  Iterator<String> inter =list.iterator();
    	  while(inter.hasNext()){
    		  inter.next();
    	  }
      }
      long t4 = System.currentTimeMillis();//获取当前时间戳
      
      System.out.println("普通："+(t2-t1));//第一
      System.out.println("增强："+(t3-t2));//第三
      System.out.println("迭代器："+(t4-t3));//第二
	}

}
