package org.qcun.wx.message;

public class FromImageMessage extends FromMessageWithMsgId
{
  private String picUrl;

  public FromImageMessage()
  {
    super.setMsgType("image");
  }

  public String getPicUrl()
  {
    return this.picUrl;
  }

  void setPicUrl(String picUrl)
  {
    this.picUrl = picUrl;
  }
}