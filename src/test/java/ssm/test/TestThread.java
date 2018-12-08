/*package ssm.test;

public class TestThread extends Thread{

	private int count = 5;
	private String name;
	public TestThread(String name) {
	   this.name = name;
	}
	
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(name+"运行 count="+count--);
			try {
				sleep((int)Math.random()*10);
			} catch (InterruptedException  e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		TestThread t1 = new TestThread("A");
		TestThread t2 = new TestThread("B");
		t1.run();
		t2.run();
	}
	
}
*/