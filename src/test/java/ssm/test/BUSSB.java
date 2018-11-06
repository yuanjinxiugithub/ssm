package ssm.test;

public class BUSSB extends Thread{
	 LockMethod lockMethod;
	  void deal(LockMethod lockMethod){
	      this.lockMethod = lockMethod;
	  }
	  @Override
	  public void run() {
	      super.run();
	      lockMethod.busiB();
	  }
}
