package DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yjx
 *  秘书进行操作 为代理类
 */
public class SecretaryInvokeHandler implements InvocationHandler{
    private Object object;
    
    public SecretaryInvokeHandler(Object object) {
        super();
        this.object = object;
    }

    /**   
     * @Title: geInstance   
     * @Description: TODO  创建代理实例
     * @param: @param cls
     * @param: @return      
     * @return: Object      
     * @throws   
     */  
    public Object geInstance(Class cls){
        Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(),  new Class[] { cls }, this);
        return newProxyInstance;//返回委托实例
    }

    /* (non-Javadoc)
     *  method 为传入的方法 args即为传入的method方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//proxy 为代理方 proxy为代理类的实例 
        // TODO Auto-generated method stub
        System.out.println("我是秘书，为代理方");
        Object res = method.invoke(object, args);//代理执行
        return res;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
