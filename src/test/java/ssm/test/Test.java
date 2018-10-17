package ssm.test;

public class Test {
	
	public  static void main(String[] args){
		int i;
	
		 for (i=0;i<5;i++)
		    {
		        if (i==3) break;
		    	String x="";
		        x=x + "The number is " + i + "<br>";
             System.out.println(x);
		       
		    }
		    //  只输出 0 ， 1 ， 2 ， 到3就跳出循环了
		    for (i=0;i<=5;i++)
		    {
		        if (i==3) continue;
		        String x="";
		        x=x + "The number is " + i + "<br>";
		        System.out.println(x);
		    }
		    //  不输出3，因为continue跳过了，直接进入下一个迭代
	}

}
