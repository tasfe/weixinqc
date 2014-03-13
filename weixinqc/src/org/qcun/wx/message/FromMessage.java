package org.qcun.wx.message;

public class FromMessage {
	private String toUserName;
	private String fromUserName;
	private String msgType;
	private String createTime;
	private String msgId;

	public FromMessage() {
	}

	public FromMessage(String toUserName, String fromUserName, String msgType,
			String createTime,String msgId) {
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.msgType = msgType;
		this.createTime = createTime;
		this.msgId = msgId;
	}

	public String getToUserName() {
		return this.toUserName;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public String getMsgId(){
		return this.msgId;
	}
	protected void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}