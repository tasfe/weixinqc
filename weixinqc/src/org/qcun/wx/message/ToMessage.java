package org.qcun.wx.message;
public class ToMessage
{
  private String toUserName;
  private String fromUserName;
  private String msgType;
  private String createTime;
  private String onTime;

  public String getToUserName()
  {
    return this.toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public String getFromUserName() {
    return this.fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public String getMsgType() {
    return this.msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getOnTime() {
    return this.onTime;
  }

  public void setOnTime(String onTime) {
    this.onTime = onTime;
  }
}