package ssm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ssm.domain.User;

public class TestLambda {
	

	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		
		List<User> userList = new ArrayList<>();
		
		for(int i=0;i<3;i++){
			User userObj = new User();
			userObj.setUserid("user"+i);
			userObj.setUsername("user"+i);
			userList.add(userObj);
		}

		List<User> result = new ArrayList<>();

		// 使用lambda表达式过滤出结果并放到result列表里，written by zhangchao
		result  = userList.stream().filter((User a)->"user1".equals(a.getUserid())).collect(Collectors.toList());
		
		//打印结果集
		if(result != null && !result.isEmpty()){
			result.forEach((User user)->System.out.println(user.getUsername()));
		}
		
		//filterUerList(userList,ele->((User)a).getUsername().equals("1"));
		
		filterList(languages,(str)->str.equals("Java"));
	}
	public static void filterUerList(List<User> list,Predicate p){
		List<User> filterList = new ArrayList<>();
		for(User str:list){
			if(p.test(str)){
				filterList.add(str);
			}
		}
		System.out.println(filterList.size());
	}
	
	public static void filterList(List<String> list,Predicate p){
		List<String> filterList = new ArrayList<>();
		for(String str:list){
			if(p.test(str)){
				filterList.add(str);
			}
		}
	}
}
