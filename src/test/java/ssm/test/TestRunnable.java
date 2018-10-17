package ssm.test;

public class TestRunnable implements Runnable{
	private int count = 5;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++){
			System.out.println(Thread.currentThread().getName()+"运行 count="+count--);
			/*try {
				//Thread.sleep((int) Math.random() * 10);
			} catch (InterruptedException  e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
		}
	}
	public static void main(String[] args) {
		TestRunnable run1 = new TestRunnable();
		new Thread(run1,"A").start();
		new Thread(run1,"B").start();
	}
}
