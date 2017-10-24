package com.sunwuo.electronic_mall.websocket;

import javax.websocket.Session;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	private String ID;
	private Session session;
	private String CreateTime;
	private String EndTime;
	private String ClientType;
	
	
	
	public Client(String iD, Session session, String clientType) {
		super();
		ID = iD;
		this.session = session;
		ClientType = clientType;
		this.CreateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getClientType() {
		return ClientType;
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
	}
	
	
	public void SendMsg(String msg){
		try {
			this.session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
