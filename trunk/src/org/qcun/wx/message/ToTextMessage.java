package org.qcun.wx.message;
public class ToTextMessage extends ToMessage
{
  private String content;

  public ToTextMessage()
  {
    super.setMsgType("text");
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }
}