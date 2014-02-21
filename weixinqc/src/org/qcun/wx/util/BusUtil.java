package org.qcun.wx.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class BusUtil {

	private final static String app_key = "9387782cf8562e922b278d857ecb7352";

	private static String getXmlStr(String city, String line) {
		String xml = "";
		try {
			if (city == null || "".equals(city)) {

			} else {
				String citystr = URLEncoder.encode(city, "UTF-8");
				String linestr = URLEncoder.encode(line, "UTF-8");
				URL url = new URL(
						"http://openapi.aibang.com/bus/lines?app_key="
								+ app_key + "&city=" + citystr + "&q="
								+ linestr);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// conn.setDoOutput(true);
				conn.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "UTF-8"));
				String aline;
				while ((aline = in.readLine()) != null) {
					xml += aline;
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	public static String getBuslineStr(String string) {
		String[] arr = string.split("\\s+");
		StringBuffer str = new StringBuffer();
		String xml = "";
		if (arr.length != 2) {
			return "请输入正确格式。";
		} else {
			String city = arr[0];
			String line = arr[1];
			xml = getXmlStr(city, line);
			if (xml == null || "".equals(xml)) {
			} else {
				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder db = factory.newDocumentBuilder();
					Document doc = db.parse(new InputSource(new StringReader(xml)));
					Element element = doc.getDocumentElement();
					Node result_numNode = element.getFirstChild();
					int result_num = Integer.valueOf(
							result_numNode.getFirstChild().getNodeValue()).intValue();
					if (result_num > 0) {
						Node lines = null;
						NodeList nodeList = element.getChildNodes();
						for (int i = 0; i < nodeList.getLength(); i++) {
							Node currnode = nodeList.item(i);
							if (currnode.getNodeType() == Node.ELEMENT_NODE
									&& currnode.getNodeName().equals("lines")) {
								lines = currnode;
								break;
							}
						}
						Node aline = lines.getFirstChild();
						NodeList alinelist = aline.getChildNodes();
						String name = "";
						String info = "";
						String stats = "";
						for (int i = 0; i < alinelist.getLength(); i++) {
							Node currnode = alinelist.item(i);
							if (currnode.getNodeType() == Node.ELEMENT_NODE
									&& currnode.getNodeName().equals("name")) {
								name = currnode.getFirstChild().getNodeValue();
							}
							if (currnode.getNodeType() == Node.ELEMENT_NODE
									&& currnode.getNodeName().equals("info")) {
								info = currnode.getFirstChild().getNodeValue();
							}
							if (currnode.getNodeType() == Node.ELEMENT_NODE
									&& currnode.getNodeName().equals("stats")) {
								stats = currnode.getFirstChild().getNodeValue();
							}
						}
						str.append("【线路名称】\n");
						str.append(name).append("\n");
						str.append("【线路信息】\n");
						str.append(info).append("\n");
						str.append("【沿途站点】\n");
						str.append(stats).append("\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		if (str == null || "".equals(str.toString())) {
			str.append("没有查询到相关站点信息...").append(
			"<a href='weixin://addfriend/maytWX'>请联系作者</a>");
		}
		return str.toString();
	}

}
