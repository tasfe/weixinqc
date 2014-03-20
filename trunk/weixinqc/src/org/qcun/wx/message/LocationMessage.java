package org.qcun.wx.message;

import org.qcun.wx.util.WeatherUtil;


public class LocationMessage {

	private FromMessage fromMessage;
	private Location location;
	
	public LocationMessage(FromMessage fromMessage,Location location){
		this.fromMessage = fromMessage;
		this.location = location;
	}
	public String deal() {
		
		
		return new ToTextMessage(this.fromMessage, WeatherUtil
				.getWeatherStr(this.location)).initReqStr();
	}
}
