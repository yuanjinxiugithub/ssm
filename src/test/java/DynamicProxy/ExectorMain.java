package DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ExectorMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ReadFile file = new BossReadFileImpl();
        InvocationHandler  invokeHandler = new SecretaryInvokeHandler(file);
        ReadFile proxy = (ReadFile) Proxy.newProxyInstance(file.getClass().getClassLoader(), file.getClass().getInterfaces(), invokeHandler);
    /*    proxy.readFile(null);*/
        
        ReadFile proxy2 = (ReadFile) new SecretaryInvokeHandler(file).geInstance(ReadFile.class);
        proxy2.readFile(null);
    }

}
