package org.qcun.wx.message;
public class FromMessage
{
  private String toUserName;
  private String fromUserName;
  private String msgType;
  private String createTime;

  
  public FromMessage(){}
  public FromMessage(String toUserName,String fromUserName,String msgType,String createTime){
	  this.toUserName = toUserName;
	  this.fromUserName = fromUserName;
	  this.msgType = msgType;
	  this.createTime = createTime;
  }
  public String getToUserName()
  {
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

  protected void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  protected void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  protected void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  protected void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}