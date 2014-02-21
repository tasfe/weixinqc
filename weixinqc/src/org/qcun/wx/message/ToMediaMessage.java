package org.qcun.wx.message;

import org.qcun.wx.util.WeixinUtil;

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

@Override
protected String initMsgStr() {
	return "";
}
}