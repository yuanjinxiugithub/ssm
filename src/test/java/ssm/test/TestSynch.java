package ssm.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author yjx
 *   异步加载数据
 */
public class TestSynch {
	private static int money ;
	
	public void orderMoudle(){
		 Random generate = new Random();
		money = generate.nextInt(1000);
		System.out.println(money+"---------------------------------------------");
		System.out.println(Thread.currentThread().getName()+"下单的金额为："+money);
	}
	
	public void orderDetailMoudle(){
		System.out.println(Thread.currentThread().getName()+"详情获取订单的金额为："+money);
	}
	
	public void saleModule(){
		System.out.println(Thread.currentThread().getName()+"销售获取订单的金额为："+money);
	}
	
	public void handlerOrder(){
		//同步锁 需要等待
		synchronized (TestSynch.class) {//class 锁
			orderMoudle();
			orderDetailMoudle();
			saleModule();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*TestSynch sy = new TestSynch();
		sy.handlerOrder();*/
		
		//测试并发
		int count = 10;
		final TestSynch sy = new TestSynch();  //final 不可修改的类
		
		CountDownLatch counLatch = new CountDownLatch(count);
		for(int i=0;i<count;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sy.handlerOrder();
					counLatch.countDown(); //countDownLantch 减1
				}
			}).start();;
		}
	
		try {
			counLatch.await(); //调用此方法会一直阻塞当前线程，不会向下执行，直到计时器的值为0的时候程序才会继续向下执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
