package org.qcun.wx.message;

public abstract class FromMessageWithMsgId extends FromMessage
{
  private double msgId;

  public double getMsgId()
  {
    return this.msgId;
  }

  protected void setMsgId(double msgId)
  {
    this.msgId = msgId;
  }
}