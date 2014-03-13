package org.qcun.wx.message;

import java.util.Date;

public class ToMessage {
	/**
	 * 接收方帐号（收到的OpenID）
	 */
	private String toUserName;
	/**
	 * 开发者微信号
	 */
	private String fromUserName;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 消息创建时间 （整型）
	 */
	private String createTime;

	/**
	 * 构造方法
	 */
	public ToMessage() {

	}

	public ToMessage(FromMessage fromMessage) {
		this.fromUserName = fromMessage.getFromUserName();
		this.toUserName = fromMessage.getToUserName();
		this.msgType = fromMessage.getMsgType();
		this.createTime = String.valueOf((new Date()).getTime() / 1000);

	}

	/**
	 * 构造消息可发送字符串
	 * 
	 * @return
	 */
	
	public String getToUserName() {
		return this.toUserName;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	public String getMsgType() {
		return this.msgType;
	}

	protected void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getCreateTime() {
		return this.createTime;
	}

}