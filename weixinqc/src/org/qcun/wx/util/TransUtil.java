package org.qcun.wx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class TransUtil {
	private final static String key = "1058726328";
	private final static String keyfrom = "mzaigz";

	public static String getTransStr(String str) {
		StringBuffer result = new StringBuffer();
		String json = getJsonStr(str);
		// String xml = getXmlStr(str);

		try {
			/*
			 * //xml解析代码 DocumentBuilderFactory factory =
			 * DocumentBuilderFactory.newInstance(); DocumentBuilder db =
			 * factory.newDocumentBuilder(); Document doc = db.parse(xml);
			 * Element element = doc.getDocumentElement(); NodeList nodes =
			 * element.getChildNodes(); int errorCode = -1; String result_temp =
			 * ""; for (int i = 0; i < nodes.getLength(); i++){ Node currnode =
			 * nodes.item(i); if (currnode.getNodeType() == Node.ELEMENT_NODE &&
			 * currnode.getNodeName().equals("errorCode")){ errorCode =
			 * Integer.valueOf(currnode.getNodeValue()).intValue(); }
			 * if(currnode.getNodeType() == Node.ELEMENT_NODE &&
			 * currnode.getNodeName().equals("translation")){ Node transnode =
			 * currnode.getFirstChild(); result_temp = transnode.getNodeValue();
			 * } }
			 */

			JSONObject obj = new JSONObject(json);
			int errorCode = obj.getInt("errorCode");
			switch (errorCode) {
			case 0:
				JSONObject basic = null;
				if(obj.has("basic")){
					basic = obj.getJSONObject("basic");
				}
				if (basic != null) {
					if (basic.has("phonetic")) {
						String phonetic = basic.getString("phonetic");// 发音
						if (phonetic != null && !"".equals(phonetic)) {
							result.append("发音：").append(phonetic).append("\n");
						}
					}

				}
				String translation = obj.getString("translation");
				if (translation.length() > 4) {
					result.append("快译：\n");
					result.append("  ").append(
							translation.substring(2, translation.length() - 2));
					result.append("\n");
				}
				if (basic != null) {
					if (basic.has("explains")) {
						String explains = basic.getString("explains");
						if (explains.length() > 4) {
							String[] explainarr = explains.substring(1,
									explains.length() - 1).split(",");
							if (explainarr.length > 0) {
								result.append("详解：\n");
								for (int i = 0; i < explainarr.length; i++) {
									result.append("  ").append(explainarr[i])
											.append("\n");
								}
							}
						}
					}
				}
				// result = result_temp;
				break;
			case 20:
				result.append("要翻译的文本过长");
				break;
			case 30:
				result.append("无法进行有效的翻译");
				break;
			case 40:
				result.append("不支持的语言类型");
				break;
			case 50:
				result.append("无效的key").append(
						"<a href='weixin://addfriend/maytWX'>请联系作者</a>");
				break;
			default:
				result.append("出现异常.errorCode:").append(errorCode).append(
						"<a href='weixin://addfriend/maytWX'>请联系作者</a>");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null || "".equals(result.toString())) {
			result.append("没有得到翻译结果，").append(
					"<a href='weixin://addfriend/maytWX'>请联系作者</a>");
		}
		return result.toString();
	}

	/**
	 * 获取翻译后的json字符串
	 * 
	 * @param str
	 * @return
	 */
	private static String getJsonStr(String str) {
		String json = "";
		try {
			if (str == null || "".equals(str)) {

			} else {
				String qstr = URLEncoder.encode(str, "UTF-8");
				URL url = new URL(
						"http://fanyi.youdao.com/fanyiapi.do?keyfrom="
								+ keyfrom + "&key=" + key
								+ "&type=data&doctype=json&version=1.1&q="
								+ qstr);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					json += line;
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String getXmlStr(String str) {
		String xml = "";
		try {
			if (str == null || "".equals(str)) {

			} else {
				URL url = new URL(
						"http://fanyi.youdao.com/fanyiapi.do?keyfrom="
								+ keyfrom + "&key=" + key
								+ "&type=data&doctype=xml&version=1.1&q=" + str);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					xml += line;
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
}
