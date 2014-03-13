package org.qcun.wx.message;

import org.qcun.wx.util.BusUtil;
import org.qcun.wx.util.TransUtil;
import org.qcun.wx.util.WeatherUtil;
import org.qcun.wx.util.WeixinUtil;

public class TextMessage {

	private FromMessage fromMessage;
	private String content;

	public TextMessage(FromMessage fromMessage, String content) {
		this.fromMessage = fromMessage;
		this.content = content;
	}

	public String deal() {
		if (isWeather(this.content)) {
			return new ToTextMessage(this.fromMessage, WeatherUtil
					.getWeatherStr(this.content.substring(0, this.content.length() - 2)
							.trim())).initReqStr();
		}
		if (isTranslation(this.content)) {
			return new ToTextMessage(this.fromMessage, TransUtil
					.getTransStr(this.content.substring(2).trim())).initReqStr();
		}
		if (isBusline(this.content)) {
			return new ToTextMessage(this.fromMessage,BusUtil.getBuslineStr(this.content.substring(2))).initReqStr();
		}
		
		return new ToTextMessage(this.fromMessage,WeixinUtil.STR_FUNCTIONS).initReqStr();
	}

	private boolean isWeather(String msg) {
		return ((msg != null) && (!"".equals(msg)) && (msg.length() > 2) && ("天气"
				.equals(msg.substring(msg.length() - 2))));
	}

	private boolean isTranslation(String msg) {
		return ((msg != null) && (!"".equals(msg)) && (msg.length() > 2) && ("翻译"
				.equals(msg.substring(0, 2))));
	}

	private boolean isBusline(String msg) {
		return (msg != null) && (!"".equals(msg)) && (msg.length() > 2)
				&& "公交".equals(msg.substring(0, 2));
	}
}
