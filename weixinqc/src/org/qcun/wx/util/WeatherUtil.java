package org.qcun.wx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.qcun.wx.message.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherUtil {
	//百度天气调用密钥 
	private static final String BAIDU_AK = "574ed1797710ec745bf966e1e807dbbe";
	/**
	 * 获取天气城市代码
	 * 
	 * @param name
	 *            如：济南
	 * @return
	 */
	private static String getCityCode(String name) {
		String code = "";
		URL curr = WeatherUtil.class.getResource("/");
		String basePath = curr.getPath();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = factory.newDocumentBuilder();
			Document doc = db.parse(new File(basePath
					+ "org/qcun/wx/weather/data/city.xml"));
			Element elmtInfo = doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node result = nodes.item(i);
				if (result.getNodeType() == Node.ELEMENT_NODE
						&& result.getNodeName().equals("county")) {
					NamedNodeMap attrMap = result.getAttributes();
					String attr = attrMap.getNamedItem("name").getNodeValue();
					if (attr.equals(name)) {
						code = attrMap.getNamedItem("weatherCode")
								.getNodeValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return code;
	}

	/**
	 * 获取天气预报信息
	 * 
	 * @param name
	 *            如 ： 济南
	 * @return
	 */
	private static String getForecastJson(String name) {
		String str = "";
		try {
			String citycode = getCityCode(name);
			if (citycode == null || "".equals(citycode)) {

			} else {
				URL url = new URL("http://m.weather.com.cn/data/" + citycode
						+ ".html");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					str += line;
				}
				in.close();
				conn.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 获取实时天气信息
	 * 
	 * @param name
	 *            如 ： 济南
	 * @return
	 */
	private static String getRtJson(String name) {
		String str = "";
		try {
			String citycode = getCityCode(name);
			if (citycode == null || "".equals(citycode)) {

			} else {
				URL url = new URL("http://www.weather.com.cn/data/sk/"
						+ citycode + ".html");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					str += line;
				}
				in.close();
				conn.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	private static String getJsonByBaidu(Location location){
		String str="";
		try{
			if(location != null){
				URL url = new URL("http://api.map.baidu.com/telematics/v3/weather?location="+location.getLocationY()+","+location.getLocationX()+"&output=json&ak="+BAIDU_AK);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					str += line;
				}
				in.close();
				conn.disconnect();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 获取当天天气信息
	 * 
	 * @param name
	 *            如 ： 济南
	 * @return
	 */
	private static String getTodayJson(String name) {
		String str = "";
		try {
			String citycode = getCityCode(name);
			if (citycode == null || "".equals(citycode)) {

			} else {
				URL url = new URL("http://www.weather.com.cn/data/cityinfo/"
						+ citycode + ".html");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					str += line;
				}
				in.close();
				conn.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// 获取实时时间天气
	public static String getRtStr(String name) {
		StringBuffer str = new StringBuffer();
		String json = getRtJson(name);
		if (json != null && !"".equals(json)) {
			try {
				JSONObject data = new JSONObject(json);
				JSONObject obj = data.getJSONObject("weatherinfo");
				str.append("【实时天气】\n");
				str.append(obj.getString("temp")).append("℃").append(
						obj.getString("WD")).append(obj.getString("WS"))
						.append("\n");
				str.append("湿度：").append(obj.getString("SD")).append("\n");
				str.append("发布时间：").append(obj.getString("time")).append("\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str.toString();
	}

	public static String getTodayStr(String name) {
		StringBuffer str = new StringBuffer();
		String json = getTodayJson(name);
		if (json != null && !"".equals(json)) {
			try {
				JSONObject data = new JSONObject(json);
				JSONObject obj = data.getJSONObject("weatherinfo");
				str.append("【今日天气】\n");
				str.append(obj.getString("weather")).append(
						obj.getString("temp2")).append("~").append(
						obj.getString("temp1")).append("\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str.toString();
	}

	public static String getForecastStr(String name) {
		StringBuffer str = new StringBuffer();
		String json = getForecastJson(name);
		if (json != null && !"".equals(json)) {
			try {
				JSONObject data = new JSONObject(json);
				JSONObject obj = data.getJSONObject("weatherinfo");
				Calendar cal = Calendar.getInstance();
				int day = cal.get(Calendar.DATE);
				int month = cal.get(Calendar.MONTH) + 1;
				int year = cal.get(Calendar.YEAR);
				String today = year + "年" + month + "月" + day + "日";
				if (today.equals(obj.getString("date_y"))) {
					str.append("【预报天气】\n");
					str.append("明天：\n");
					str.append(obj.getString("weather2")).append(
							obj.getString("temp2")).append(
							obj.getString("wind2")).append("\n");
					str.append("后天：\n");
					str.append(obj.getString("weather3")).append(
							obj.getString("temp3")).append(
							obj.getString("wind3")).append("\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str.toString();
	}

	public static String getStrByBaidu(Location location){
		StringBuffer str = new StringBuffer();
		String json = getJsonByBaidu(location);
		if(json!=null && !"".equals(json)){
			try{
				JSONObject data = new JSONObject(json);
				String status = data.getString("status");
				if(status.equals("success")){
					JSONArray results = data.getJSONArray("results");
					JSONObject current = results.getJSONObject(0);
					String currentCity = current.getString("currentCity");
					str.append(currentCity).append("天气:\n");
					JSONArray weather_data = current.getJSONArray("weather_data");
					for(int i = 0; i < weather_data.length();i++){
						String date = weather_data.getJSONObject(i).getString("date");
						String weather = weather_data.getJSONObject(i).getString("weather");
						String wind = weather_data.getJSONObject(i).getString("wind");
						String temperature = weather_data.getJSONObject(i).getString("temperature");
						str.append("★").append(date).append("\n");
						str.append(" ¤").append(weather).append("\n");
						str.append(" ¤").append(wind).append("\n");
						str.append(" ¤").append(temperature).append("\n");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return str.toString();
	}
	
	/**
	 * 获取天气
	 * 
	 * @param name
	 * @return
	 */
	public static String getWeatherStr(String name) {
		StringBuffer str = new StringBuffer();
		String realtime = getRtStr(name);
		String today = getTodayStr(name);
		String forecast = getForecastStr(name);
		if (realtime != null && !"".equals(realtime)) {
			str.append(realtime);
		}
		if (today != null && !"".equals(today)) {
			str.append(today);
		}
		if (forecast != null && !"".equals(forecast)) {
			str.append(forecast);
		}

		if ("".equals(str.toString()) || str == null) {
			str.append("对不起，没有查询到").append(name).append("天气信息...").append(WeixinUtil.STR_CONTACT);
		}
		return str.toString();
	}
	public static String getWeatherStr(Location location){
		return getStrByBaidu(location);
	}
}
