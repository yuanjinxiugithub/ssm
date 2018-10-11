package com.ssm.base.util;

/**数组处理工具类
 * @author yjx
 *
 */
public class ArraysUtil {
	/**
	 * <p>Title: 构造函数</p> 
	 * <p>Description: 私有构造函数</p>  
	 */
	private ArraysUtil(){
	}

	/**
	 * 将集合使用指定的分隔符合并为字符串
	 * objs is null , return null
	 * objs.length == 0 , return ""
	 * objs{"aaa","bbb","ccc"} , return "aaa,bbb,ccc"
	 * @param objs 带合并的数组
	 * @param splitChar 分隔符，默认为英文逗号
	 * @return 拼接后的字符串结果
	 */
	public static String toString(Object[] objs , String splitChar){
		if(ObjectUtil.assertNull(objs)){
			return null;
		}
		if(ObjectUtil.assertNull(splitChar)){
			splitChar = ",";
		}
		int max = objs.length;
		if(max < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(Object obj : objs){
			if(ObjectUtil.assertNotNull(obj)){
				sb.append(String.valueOf(obj));
				if(i<max-1){
					sb.append(splitChar);
				}
			} else if (i == max -1 && sb.length() > 0){
				sb.delete(sb.length() - splitChar.length(), sb.length());
			}
			i++;
		}
		return sb.toString();
	}
	
	/**
	* <p>Title: hasElement</p> 
	* <p>Description: 判断当前数组中是否包含元素 </p>
	* @param objs 待验证的数组
	* @return 包含true，否则false
	 */
	public static boolean hasElement(Object[] objs){
		if(ObjectUtil.assertNull(objs)){
			return false;
		}
		return objs.length > 0 ? true : false ;
	}
	
	/**
	* <p>Title: contains</p> 
	* <p>Description: 检测集合中是否包含某个对象，使用equals进行比较 </p>
	* @param objs 集合对象
	* @param target 目标对象
	* @return 包含true，否则false
	 */
	public static boolean contains(Object[] objs , Object target){
		if(!ArraysUtil.hasElement(objs)){
			return false;
		}
		for(Object obj : objs){
			if(target.equals(obj)){
				return true;
			}
		}
		return false;
	}
}
