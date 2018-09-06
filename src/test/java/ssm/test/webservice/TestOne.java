package ssm.test.webservice;

import java.net.MalformedURLException;

import com.ssm.webservice.HelloService;
import com.ssm.wsclient.HelloServiceImplService;

/**
 * @author yjx
 * web service 客户端调用案例 根据apach-cxf生成客户端的类文件
 */
public class TestOne {

	public static void main(String[] args) throws MalformedURLException {
	HelloServiceImplService serviceFactory  = new HelloServiceImplService (); //调用生成的实现方法
	HelloService service  =   serviceFactory.getHelloServiceImplPort();
	String result = service.testHello("Jaune");  
    System.out.println(result);  

	
	}

}
