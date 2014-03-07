package org.qcun.wx.message;

public class FromLinkMessage extends FromMessageWithMsgId
{
  private String title;
  private String description;
  private String url;

  public FromLinkMessage()
  {
    super.setMsgType("link");
  }

  public String getTitle()
  {
    return this.title;
  }

  void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  void setDescription(String description) {
    this.description = description;
  }

  String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}