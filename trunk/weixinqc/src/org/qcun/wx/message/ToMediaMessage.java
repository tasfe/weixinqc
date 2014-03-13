package org.qcun.wx.message;


public class ToMediaMessage extends ToMessage {
	private String title;
	private String description;

	public ToMediaMessage() {
		super.setMsgType("media");
	}

	/**
	 * 构造方法
	 * @param fromMessage
	 * @param title
	 * @param description
	 */
	public ToMediaMessage(FromMessage fromMessage, String title, String description) {
		super(fromMessage);
		super.setMsgType("media");
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
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