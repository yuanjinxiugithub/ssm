package DynamicProxy;

/**
 * @author yjx
 *  Boss 实现读文件 为委托类 
 */
public class BossReadFileImpl implements ReadFile{

    @Override
    public void readFile(String str) {
        // TODO Auto-generated method stub
       System.out.println("输入参数为:"+str);
       System.out.println("read file。。。。");
    }

}
