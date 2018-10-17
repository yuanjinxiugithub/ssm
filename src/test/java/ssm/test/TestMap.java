package ssm.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.cxf.ws.addressing.EndpointReferenceType;

/**
 * @author yjx
 * 测试map遍历
 */
public class TestMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++){
			hashmap.put(""+i, "thanks");
		}
		
		long t1 = System.currentTimeMillis();
		
		Iterator<String> itr = hashmap.keySet().iterator();
		
		while(itr.hasNext()){
		 System.out.print(hashmap.get(itr.next()));
	    }
		long t2 = System.currentTimeMillis();
		
		Iterator<Entry<String, String>> iter = hashmap.entrySet().iterator();
		
		while(iter.hasNext()){  //迭代器的遍历
			  System.out.println("key:"+iter.next().getKey()+";value:"+iter.next().getValue());
		  }
		long t3 = System.currentTimeMillis();
		
		
		for(Entry<String, String> enrty:hashmap.entrySet()){
                 System.out.println("key:"+enrty.getKey()+";value:"+enrty.getValue());
		}
		
		long t4 = System.currentTimeMillis();
		
	    System.out.println("第一:"+(t2-t1));  //慢
		
		System.out.println("第二:"+(t3-t2));  //快
		
		System.out.println("第二:"+(t4-t3));  //第二
	}

}
