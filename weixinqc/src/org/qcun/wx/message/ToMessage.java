package org.qcun.wx.message;
public abstract class ToMessage
{
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
   * 构造消息可发送字符串
   * @return
   */
  protected abstract String initMsgStr();
  
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
  
}