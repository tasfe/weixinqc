package org.qcun.wx.message;

import org.qcun.wx.util.WeixinUtil;

public class ToNewsMessage extends ToMessage {
	private int articleCount;
	private TextImageMessage[] articles;

	public ToNewsMessage(){
		super.setMsgType("news");
		this.articleCount = 0;
		this.articles = null;
	}
	public ToNewsMessage(FromMessage fromMessage, TextImageMessage[] articles) {
		super(fromMessage);
		super.setMsgType("news");
		this.articles = articles;
		this.articleCount = articles.length;
	}

	public int getArticleCount() {
		return this.articleCount;
	}

	public TextImageMessage[] getArticles() {
		return this.articles;
	}

	@Override
	protected String initMsgStr() {
		String str = WeixinUtil.OutFormatMsg(this);
		return str;
	}
}