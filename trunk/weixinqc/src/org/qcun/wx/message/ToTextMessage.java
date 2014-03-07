package org.qcun.wx.message;

import org.qcun.wx.util.WeixinUtil;

public class ToTextMessage extends ToMessage
{
  private String content;

  public ToTextMessage()
  {
    super.setMsgType("text");
  }
  /**
   * 构造方法
   * @param fromMessage
   * @param content
   */
  public ToTextMessage(FromMessage fromMessage,String content){
	  super(fromMessage);
	  super.setMsgType("text");
	  this.setContent(content);
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  
  
	@Override
	protected String initMsgStr() {
		String str = WeixinUtil.OutFormatMsg(this);
		return str;
	}
}