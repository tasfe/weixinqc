package org.qcun.wx.message;
public class FromTextMessage extends FromMessageMsgId
{
  private String content;

  public FromTextMessage()
  {
    super.setMsgType("text");
  }

  public String getContent()
  {
    return this.content;
  }

  void setContent(String content)
  {
    this.content = content;
  }
}