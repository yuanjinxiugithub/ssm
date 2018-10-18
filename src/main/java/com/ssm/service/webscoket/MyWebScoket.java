package com.ssm.service.webscoket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.ssm.base.util.JSONFormatter;
import com.ssm.domain.User;

/**
 * @author yjx
 *该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。
 *configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */
@ServerEndpoint(value = "/websocket",configurator = SpringConfigurator.class)
public class MyWebScoket {
    private static int onlineCount = 0; //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	
    
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    // 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebScoket> webSocketSet = new CopyOnWriteArraySet<MyWebScoket>();//webscoket 集合

  //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

	public MyWebScoket() {}
	
	/**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
      //  addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws IOException 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for(MyWebScoket item: webSocketSet){
            item.sendMessage(message,null);
        }
    }
    
    
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    
    
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。--自定义方法
     * @param message
     * @throws IOException
     */
    public static void sendMessage(String message, List<User> user) throws IOException{
        //保存数据到数据库
    	System.out.println("接受到的消息是:"+message);
    	for (MyWebScoket client : webSocketSet) {//群发消息
    		synchronized (client)
    		{
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("success", true);
    			map.put("type", "1");
    			map.put("message", "web scoket 推送消息成功");
    			map.put("data", user);
    			String data = JSONFormatter.newInstance().toJSONString(map);
        		client.session.getBasicRemote().sendText(data);
    		}
    		
    	}
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
    	MyWebScoket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
    	MyWebScoket.onlineCount--;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
