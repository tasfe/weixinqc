package org.qcun.wx.message;

public class Location {

	/**
	 * 地理位置维度
	 */
	private String locationX;
	/**
	 * 地理位置经度
	 */
	private String locationY;
	/**
	 * 地图缩放大小
	 */
	private String scale;
	/**
	 * 地理位置信息
	 */
	private String label;
	
	public Location(String locationX,String locationY,String scale,String label){
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
	}

	public String getLocationX() {
		return locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public String getScale() {
		return scale;
	}

	public String getLabel() {
		return label;
	}
	/**
	 * 根据位置获取所在城市
	 */
	public String getCityName(){
		
		return "";
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("位置信息：{");
		sb.append("[纬度：");
		sb.append(this.locationX);
		sb.append("][经度：");
		sb.append(this.locationY);
		sb.append("][地图缩放大小：");
		sb.append(this.scale);
		sb.append("][位置信息：");
		sb.append(this.label);
		sb.append("]}");
		return sb.toString();
	}
}
