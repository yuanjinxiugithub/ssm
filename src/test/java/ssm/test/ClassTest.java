package ssm.test;


public class ClassTest {
	public  static void main(String[] args){
		System.out.println(ClassTest.class.getName()); //Class.getName 获取对象所在的实体(类、接口、数组类、基本类型或void)名称
		System.out.println(ClassTest.class.getClassLoader()); //Class.getClassLoader 返回该类的类加载器。  加载此对象所表示的类或接口或接口的类加载器
		System.out.println(ClassTest.class.getInterfaces()); //Class.getInetrfaces 获取此对象所表示的类或接口实现的接口 返回该类所实现的接口的一个数组
	}
}
