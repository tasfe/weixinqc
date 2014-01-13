package org.qcun.wx.message;

public class ToMusicMessage extends ToMediaMessage
{
  private String musicUrl;
  private String hQMusicUrl;

  public ToMusicMessage()
  {
    super.setMsgType("music");
  }

  public String getMusicUrl()
  {
    return this.musicUrl;
  }

  public void setMusicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
  }

  public String gethQMusicUrl() {
    return this.hQMusicUrl;
  }

  public void sethQMusicUrl(String hQMusicUrl) {
    this.hQMusicUrl = hQMusicUrl;
  }
}