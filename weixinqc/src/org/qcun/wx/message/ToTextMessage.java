package org.qcun.wx.message;

import org.qcun.wx.util.WeixinUtil;

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

	@Override
	protected String initMsgStr() {
		String str = WeixinUtil.OutFormatMsg(this);
		return str;
	}
}