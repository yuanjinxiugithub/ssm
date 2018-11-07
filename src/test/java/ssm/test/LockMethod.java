package ssm.test;

public class LockMethod {
	
	private static final String Object = "1";
	
	private static final String Object2 = "2";

	public synchronized void  busiA(){
		//synchronized(Object){
			for(int i=0;i<100;i++){
				System.out.println(Thread.currentThread().getName()+"deal with bussiness A:"+i);
			}
		//}
	}
	
	public synchronized void  busiB(){
		//synchronized(Object2){
			for(int i=0;i<100;i++){
				System.out.println(Thread.currentThread().getName()+"deal with bussiness B:"+i);
			}
	   //	}
	}


}
