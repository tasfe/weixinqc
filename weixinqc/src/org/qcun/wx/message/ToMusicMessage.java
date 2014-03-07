package org.qcun.wx.message;

public class ToMusicMessage extends ToMediaMessage
{
  private String musicUrl;
  private String hQMusicUrl;

  public ToMusicMessage()
  {
    super.setMsgType("music");
  }

  /**
   * 构造方法
   * @param fromMessage
   * @param title
   * @param description
   * @param musicUrl
   * @param hQMusicUrl
   */
  public ToMusicMessage(FromMessage fromMessage,String title,String description,String musicUrl,String hQMusicUrl){
	  super(fromMessage,title,description);
	  super.setMsgType("music");
	  this.setMusicUrl(musicUrl);
	  this.sethQMusicUrl(hQMusicUrl);
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