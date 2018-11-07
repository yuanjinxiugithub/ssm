package ssm.test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockTest extends Thread {
	     @Override
	    public void run() {
	    	 ReentranLockTest.buy2();
	    }
	
    private static ReentrantLock lock = new ReentrantLock();  //默认为非公平的锁
    private static int i = 6;
    
    public static  void buy(){
    	try{
    	lock.lock();  //如果被其他资源锁定 会在此等待锁释放 达到暂停的效果
    	i--;
    	System.out.println(i);
    	}finally{
    		lock.unlock();
    	}
    }
    
    public static  void buy2(){
    	if(lock.tryLock()){ //如果已经被lock 则立刻返回false不会等待 达到忽略操作的效果
    		try {
				i--;
				System.out.println(i);
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				lock.unlock();
			}
    	}
    }
    public static void main(String[] args){
        ReentranLockTest demo01 = new ReentranLockTest();
        ReentranLockTest demo02 = new ReentranLockTest();
        ReentranLockTest demo03 = new ReentranLockTest();
        demo01.start();
        demo02.start();
        demo03.start();
	}
}
