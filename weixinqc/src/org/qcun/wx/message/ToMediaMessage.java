package org.qcun.wx.message;
public class ToMediaMessage extends ToMessage
{
  private String title;
  private String description;

  public ToMediaMessage()
  {
    super.setMsgType("media");
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}