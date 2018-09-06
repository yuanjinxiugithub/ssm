package com.ssm.base.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**Object对象处理工具类
 * @author yjx
 *
 */
public class ObjectUtil {
	/**
	 * <p>Title: 构造函数</p> 
	 * <p>Description: 私有构造函数</p>  
	 */
	private ObjectUtil(){
	}

	/**
	* <p>Title: assertNotNull</p> 
	* <p>Description: 判断对象是否不为空</p>
	* @param obj 待验证对象
	* @return 如果为NULL返回false，否则返回true
	 */
	public static boolean assertNotNull(Object obj){
		boolean result = null == obj ? false : true;
		return result;
	}
	
	/**
	* <p>Title: assertNull</p> 
	* <p>Description: 验证对象是否为空 </p>
	* @param obj 待验证对象
	* @return 如果为NULL返回true，否则返回false
	 */
	public static boolean assertNull(Object obj){
		boolean result = null != obj ? false : true;
		return result;
	}

	/**
	* <p>Title: getSystemEnv</p> 
	* <p>Description: 获取系统级的环境变量 </p>
	* @param name 环境变量名称
	* @return 环境变量取值
	 */
	public static String getSystemEnv(String name){
		return System.getenv(name);
	}
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null){
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0){
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
