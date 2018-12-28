package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yjx
 *  反射机制的学习 Method类中常用的方法
 */
public class MethodReflect {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // TODO Auto-generated method stub
       Class<?> heroClass = HerosPo.class;
       //获取类中所有的方法
       Method [] methods = heroClass.getMethods();
       for(Method method:methods){
           System.out.println(method.getName()); //获取方法名称
       }
       //调用类中的方法
       Method methodSetName = heroClass.getMethod("setName", String.class);// 参数类型
       Method methodGetName = heroClass.getMethod("getName");
       Object herosPo = heroClass.newInstance(); //有该类创建的创建一个新实例
       methodSetName.invoke(herosPo,"yuanjinxiu");
       System.out.println("调用set方法(创建的实例为):"+herosPo);
       System.out.println("调用getName方法:"+methodGetName.invoke(herosPo));
    }

}
