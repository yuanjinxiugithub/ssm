/*package com.ssm.redis;


import redis.clients.jedis.Jedis;

public class TestRedis {
	 @Autowired 
	  private static JedisPool jedisPool;
	 
	  public static void main(String[] args) {
		  	Jedis jedis = null;
		  	try {
				jedis = new Jedis("127.0.0.1");
			//	SimpleExample(jedis);
				PublishExample(jedis,5);
			} finally {
				// TODO: handle finally clause
				if(jedis != null){
					System.out.println("111");
					jedis.close();
					jedis.disconnect();
				}
				
			}
	  }
	  
	    //简单添加信息
	    public static void SimpleExample(Jedis redis){
	    	redis.set("TEST:KEY1", "I am simple value 1");
	    	String  str = redis.get("TEST:KEY1");
	    	System.out.println(str);
	    }
	    
	    //队列添加信息
	    public static void ListExample(Jedis redis,int number){
	    	String messageStr = "";
	    	int count = 0;
	    	  if(redis.exists("TEST:LIST")){
           	   redis.del("TEST:LIST");
              }
	    	while (count++ < number) {
               messageStr = "this is "+count+" message";
	    		redis.lpush("TEST:LIST",messageStr);
                System.out.println(messageStr);	    		
			}
	    }
	    
	    //发布订阅
	    public static void PublishExample(Jedis redis,int number){
	            String messageStr = "";
	            int count = 0;
	            while (count++ <number) {
	            	messageStr = "this is "+count+" message";
                 redis.publish("TEST:LIST", messageStr);
                 System.out.println(messageStr);
	            }
	    }
}
*/