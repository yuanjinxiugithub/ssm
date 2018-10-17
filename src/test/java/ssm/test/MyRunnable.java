package ssm.test;


public class MyRunnable implements Runnable{
	
	private volatile boolean active; //volatile 变量在多个线程之间共享
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		active = true;
		while(active){
			//
			System.out.println("true");
		}
	}
	
	
	public void stop(){
		active = false; //第二行
	}

	
	public static void main(String [] args){
		 MyRunnable runnable = new MyRunnable();
		 new Thread(runnable,"A").start();
	}
}
