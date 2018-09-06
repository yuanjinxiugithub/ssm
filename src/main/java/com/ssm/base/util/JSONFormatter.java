package com.ssm.base.util;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
/**
 * 基于fastjson包装的JSON处理工具类
 * @author Administrator
 * @date 2015年4月16日 下午1:33:00  
 *
 */
public  final class JSONFormatter {
	
	/**
	 * <p>Title: JSONFormatter</p> 
	 * <p>Description: 私有构造器</p>  
	 */
	private JSONFormatter(){}
	
	/**
	* <p>Title: newInstance</p> 
	* <p>Description: 通过newInstance方式获取实例对象 </p>
	* @return 当前对象
	 */
	public static JSONFormatter newInstance(){
		JSONFormatter sf = new JSONFormatter();
		return sf;
	}
	/**
	* <p>Title: addSerializeConfig</p> 
	* <p>Description: 添加针对不同类型属性格式化规则，如日期类型、boolean类型、bigdecimal类型 </p>
	* @param clazz 属性的类型
	* @param serializer 格式化规则实现，fastjson提供了ObjectSerializer较多的接口实现。
	* @return 当前对象“this”
	 */
	public JSONFormatter addSerializeConfig(Class<?> clazz , ObjectSerializer serializer){
		config.put(clazz, serializer);
		return this;
	}
	
	/**
	* <p>Title: addDateSerializeConfig</p> 
	* <p>Description: 设置日期格式化样式，对java.util.Date和java.sql.Data进行处理 </p>
	* @param datePattern 日期格式化样式，如：yyyy-MM-dd HH:mm:ss
	* @return 当前对象
	 */
	public JSONFormatter addDateSerializeConfig(String datePattern){
		if(StringUtil.isBlank(datePattern)){
			datePattern = DEF_DATE_PATTERN;
		}
		config.put(java.util.Date.class, new SimpleDateFormatSerializer(datePattern));
		config.put(java.sql.Date.class, new SimpleDateFormatSerializer(datePattern));
		return this;
	}
	
	/**
	* <p>Title: getSerializeConfig</p> 
	* <p>Description: 获取当前配置对象，用于其他自定义处理</p>
	* @return 序列化配置
	 */
	public SerializeConfig getSerializeConfig(){
		return config;
	}
	
	/**
	* <p>Title: toJSONString</p> 
	* <p>Description: 将bean转换为json </p>
	* @param object 待转换的JavaBean
	* @return jsonString json结果
	 */
	public String toJSONString(Object object){
		return JSONObject.toJSONString(object , config , sf.toArray(new SerializerFeature[sf.size()]));
	}
	
	/**
	* <p>Title: toObject</p> 
	* <p>Description: 将JSON转换为bean</p>
	* @param string jsonString
	* @param <T> 转换为Bean的类型
	* @param t 转为bean的类型
	* @return Bean的实例对象
	 */
	public <T> T toObject(String string , Class<T> t){
		DefaultJSONParser parser = new DefaultJSONParser(string);
		parser.setDateFormat(DEF_DATE_PATTERN);
		T value = (T) parser.parseObject(t);

        parser.handleResovleTask(value);

        parser.close();

        return (T) value;
	}
	
	/**
	* <p>Title: toArray</p> 
	* <p>Description: 将描述集合JSON转换为Bean集合 </p>
	* @param string jsonString
	* @param t Bean类型
	* @param <T> bean类型
	* @return Bean实例化对象的集合
	 */
	public <T> List<T>  toArray(String string , Class<T> t){
		if (string == null) {
            return null;
        }

        List<T> list;

        DefaultJSONParser parser = new DefaultJSONParser(string, ParserConfig.getGlobalInstance());
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken();
            list = null;
        } else {
            list = new ArrayList<T>();
            parser.parseArray(t, list);

            parser.handleResovleTask(list);
        }

        parser.close();

        return list;
	}
	
	/**
	* <p>Title: toArray</p> 
	* <p>Description: 将json转换为集合 </p>
	* @param string jsonString
	* @param types 不同Bean类型的类型集合
	* @return List  类型不同不能使用泛型封装
	 */
	public List<Object> toArray(String string , Type[] types){
		   if (string == null) {
	            return null;
	        }

	        List<Object> list;

	        DefaultJSONParser parser = new DefaultJSONParser(string, ParserConfig.getGlobalInstance());
	        parser.setDateFormat(DEF_DATE_PATTERN);
	        Object[] objectArray = parser.parseArray(types);
	        if (objectArray == null) {
	            list = null;
	        } else {
	            list = Arrays.asList(objectArray);
	        }

	        parser.handleResovleTask(list);

	        parser.close();

	        return list;
	}
	
	/**
	* <p>Title: addSerializerFeature</p> 
	* <p>Description: 添加序列化输出配置 </p>
	* @param feature 序列化配置
	* @return 当前对象
	 */
	public JSONFormatter addSerializerFeature(SerializerFeature feature){
		sf.add(feature);
		return this;
	}
	
	/**
	* <p>Title: changeDatePattern</p> 
	* <p>Description: 更改日期格式 </p>
	* @param datePattern 日期格式
	* @return 当前对象
	 */
	public JSONFormatter changeDatePattern(String datePattern){
		this.DEF_DATE_PATTERN = datePattern;
		return this;
	}
	
	/**
	 * 序列化输出配置
	 */
	private SerializeConfig config = new SerializeConfig();
	
	/**
	 * 默认的日期格式
	 */
	private String DEF_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 序列化输出配置
	 */
	private List<SerializerFeature> sf = new ArrayList<SerializerFeature>();
	
	
	public static Map<String,Object> toMap(String json){
		return JSON.parseObject(json, Map.class);
		}

}
