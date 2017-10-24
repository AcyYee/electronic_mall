package com.sunwuo.electronic_mall.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunwuo.electronic_mall.util.ImageStringUtil;
import com.sunwuo.electronic_mall.util.QRCodeUtil;

import javax.imageio.ImageIO;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;


@ServerEndpoint("/ws/socket")
public class MyWebSocket {

	
	public static Map<String,Client> client=new HashMap<String,Client>();
	public static Map<String,Session> login=new HashMap<String,Session>();
    /**
     * 连接成功*/
    @OnOpen
    public void onOpen(Session session) {
    	String ID=session.getRequestParameterMap().get("ID").get(0);
    	String clientName=session.getRequestParameterMap().get("client").get(0);
    	System.out.println(ID+"连接");
    	if(ID!=null&&clientName!=null)
    	client.put(ID, new Client(ID, session, clientName));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
    	String ID=session.getRequestParameterMap().get("ID").get(0);
    	if(client.containsKey(ID))
    	{
    		client.remove(ID);
    		System.out.println(ID+"断开连接");
    	}
    	if(login.containsValue(session))
    	{
			for (Entry<String, Session> entry : login.entrySet())
			{
				if (entry.getValue() == session) {
					login.remove(entry.getKey());
					System.out.println(ID+"断开连接");
					break;
				}
			}
    	}
    }

    /**
     * 收到消息
     * @param message 
    */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> json = mapper.readValue(message, Map.class);
		int protocol =Integer.valueOf(json.get("protocol"));
		switch (protocol) {
			case Protocol.SHOP_LOGIN: shoplogin(session);break;
		}
    }

    /**
     * 商户用户登录后台使用
     * @param session
     * @throws Exception 
     */
    private void shoplogin(Session session) throws Exception {
		 String uuid=UUID.randomUUID().toString();
		 login.put(uuid, session);
		 BufferedImage image= QRCodeUtil.createImage(uuid, null, true);
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 ImageIO.write(image, "jpg", baos);
		 session.getBasicRemote().sendText("data:image/png;base64,"+ ImageStringUtil.GetImageStr(baos.toByteArray())+"");
	}

	/**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(session.getRequestParameterMap().get("ID").get(0)+"发生错误");
    }

}
