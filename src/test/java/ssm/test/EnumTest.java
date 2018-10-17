package ssm.test;

import java.util.HashMap;
import java.util.Map;

public enum EnumTest {
     //向枚举中添加新的方法
	RED(1,"红色"),BLUE(2,"蓝色"),YELLOW(3,"黄色");
	
	private int index;
	private String value;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private EnumTest(int index, String value) {
		this.index = index;
		this.value = value;
	}
	
	private static Map<Integer, String> getKeyVlaue(){
		Map<Integer,String> mapObj = new HashMap<>();
		EnumTest [] enums = EnumTest.values();
		for(EnumTest obj:enums){
			mapObj.put(obj.getIndex(), obj.getValue());
		}
		return mapObj;
	}
	
	public static void main(String [] args){
		Map<Integer,String> map = EnumTest.getKeyVlaue();
		
	}
	
}
