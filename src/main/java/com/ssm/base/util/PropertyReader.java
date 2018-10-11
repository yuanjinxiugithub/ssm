package com.ssm.base.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * @author yjx 用于读取property中的属性
 *
 */
public class PropertyReader extends PropertyPlaceholderConfigurer  implements Serializable{

	/**
	 *   序列化
	 */
	private static final long serialVersionUID = -1311597598159159130L;
	
	private static final Logger log = LoggerFactory.getLogger(PropertyReader.class);
	

	protected static Properties ctxProps = null;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		Iterator<Object> it = props.keySet().iterator();
		while(it.hasNext()){
			String key = it.next().toString();
			String value = props.getProperty(key);
			log.debug("load context prop: k={};v={}", key, value);
		}
		log.debug("初始化PropertiesReader，开始加载properties配置文件中的数据！");
		ctxProps = props;
	}
	
	/**
	 * 判断PropertyReader是否进行初始化，如果没有，则抛出IllegalStateException
	 */
	private static void assertInit(){
		if(null == ctxProps){
			throw new IllegalStateException("当前PropertyReader中未进行properties文件注入，请在spring文件中进行配置！");
		}
	}
	
	/**
	 * 根据key获取配置文件中的值，在取值之前会对数据集合进行判断，如果数据集合为null，会抛出异常
	 * @param key properties配置文件中的属性名称
	 * @return java.lang.String 配置文件中属性名称相对应的属性值
	 */
	public static String getContextProperty(String key) {
		assertInit();
		return ctxProps.getProperty(key);
	}
	

}
