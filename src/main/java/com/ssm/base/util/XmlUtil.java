package com.ssm.base.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

public class XmlUtil {
	/**
	 * 创建document对象
	 * @param 相对路径
	 * @return
	 * @throws Exception
	 */
	public  static Document getDocument(String str) throws Exception
	{
		//获取dom解析器工厂
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//获取具体的解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		//解析xml文件，获取document对象
		Document document = db.parse(new File(str));
		return document;
	}
	
	/**
	 * 得到父节点的子节点的值,只限于父节点下面的子节点是末端元素
	 * @param document   xml对象
	 * @param parentSrt  父元素
	 * @param childStr   子元素
	 * @param i   第几个父元素,从0开始
	 * @return
	 */
	public  static String getChildValue(Document document,String parentSrt,String childStr,int i)
	{
		NodeList list = document.getElementsByTagName(parentSrt);
	    Element element = (Element) list.item(i);
	    if(element==null)
	    {
	    	throw new NullPointerException("指定的父节点不存在");
	    }
	    String content = element.getElementsByTagName(childStr).item(0).getFirstChild().getNodeValue();
		return content;
	}
	
	/**
	 * 将document对象写入到指定的xml文件中去
	 * @param document
	 * @param str  xml所在目录
	 * @throws Exception
	 */
	 public  static void saveDocument(Document document,String str) throws Exception
	 {
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();  
        //step10:获得一个Transformer对象  
        Transformer transformer = transformerFactory.newTransformer();  
        //step11:把document对象用一个DOMSource对象包装起来  
        Source xmlSource = new DOMSource(document);  
        //step12:建立一个存储目标对象  
        Result outputTarget = new StreamResult(new File(str));  
        //step13:生成相应的xml文件  
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.transform(xmlSource, outputTarget);  
	 }
	
	 /**
	  * 删除指定节点及其子节点
	  * @param name  节点键
	  * @param str   xml文件所在目录
	  * @return
	  * @throws Exception
	  */
	 public  static boolean delete(Document document,String name,String str) throws Exception
	 {
		 boolean result = false; 
		 NodeList nodeLists = document.getElementsByTagName(name); 
		 int len=nodeLists.getLength();
		 //遍历这个nodelist,每次都删除nodelist的第一个，因为nodelist的size是不断变化的
		 for(int i=0;i<len;i++)
		 { 
			 nodeLists.item(0).getParentNode().removeChild(nodeLists.item(0)); 
		 }
		 XmlUtil.saveDocument(document, str);
		 result=true;
		 return result;
	 }
	 
	public static void main(String[] args) throws Exception {
		Document document=XmlUtil.getDocument("E:/test.xml");
		String value=XmlUtil.getChildValue(document,"book","title",1);
		System.out.println(value);
	}
}
