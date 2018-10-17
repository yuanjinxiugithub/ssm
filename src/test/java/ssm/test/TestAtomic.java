package ssm.test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic implements Runnable{
     
	static AtomicInteger  aInteger = new AtomicInteger(0);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0;i<10000;i++){
			aInteger.getAndIncrement();
		}
	}
	
	 public static void main(String[] args) throws InterruptedException {
		    TestAtomic mt = new TestAtomic();
	        Thread t1 = new Thread(mt);
	      //  Thread t2 = new Thread(mt);
	        t1.start();
	     //   t2.start();
	        Thread.sleep(500);
	        System.out.println(TestAtomic.aInteger.get());
	    }

}
