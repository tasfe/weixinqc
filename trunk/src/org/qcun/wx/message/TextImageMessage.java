package org.qcun.wx.message;

public class TextImageMessage
{
  private String msgType = "textimage";
  private String title;
  private String description;
  private String picUrl;
  private String url;

  public String getMsgType()
  {
    return this.msgType;
  }

  public String getTitle() {
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

  public String getPicUrl() {
    return this.picUrl;
  }

  void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public String getUrl() {
    return this.url;
  }

  void setUrl(String url) {
    this.url = url;
  }
}