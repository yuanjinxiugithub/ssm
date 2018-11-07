package ssm.test;

public class TestLockMethod extends Thread{
	 public static void main(String[] args) {
	        LockMethod lockMethod = new LockMethod();
	        BUSSA bussa = new BUSSA();
	        BUSSA bussa1 = new BUSSA();
	        BUSSB bussb = new BUSSB();
	        bussa.deal(lockMethod);
	        bussa1.deal(lockMethod);
	        bussb.deal(lockMethod);
	        bussa.start();
	        bussb.start();
	        //bussa1.start();

	    }
}
