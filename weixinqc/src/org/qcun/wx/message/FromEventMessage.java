package org.qcun.wx.message;

public class FromEventMessage extends FromMessage
{
  private String event;
  private String eventKey;

  public FromEventMessage()
  {
    super.setMsgType("event");
  }

  public String getEvent()
  {
    return this.event;
  }

  void setEvent(String event) {
    this.event = event;
  }

  public String getEventKey() {
    return this.eventKey;
  }

  void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }
}