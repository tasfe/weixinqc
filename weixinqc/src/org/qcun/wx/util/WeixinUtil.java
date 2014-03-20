package org.qcun.wx.util;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.qcun.wx.message.TextImageMessage;
import org.qcun.wx.message.ToMusicMessage;
import org.qcun.wx.message.ToNewsMessage;
import org.qcun.wx.message.ToTextMessage;

public final class WeixinUtil {
	public final static String STR_THANKS = "thx 4 following with mzai\n";
	//public final static String STR_FUNCTIONS = "目前功能如下：\n*查天气，如输入：济南天气\n*查公交，如输入：公交济南 119\n*翻译，如输入：翻译I love you\n*更多内容，敬请期待...";
	public final static String STR_FUNCTIONS="使用教程：\n*新* 直接发送位置信息给我，查看天气信息\n\ncopyright@<a href='weixin://addfriend/maytWX'>π</a>";
	public final static String STR_CONTACT = "<a href='weixin://addfriend/maytWX'>请联系作者</a>";
	public static String OutFormatMsg(ToTextMessage toTextMessage) {
		String textTpl = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><FromUserName><![CDATA[%2$s]]></FromUserName><CreateTime>%3$s</CreateTime><MsgType><![CDATA[%4$s]]></MsgType><Content><![CDATA[%5$s]]></Content><FuncFlag>0</FuncFlag></xml>";

		String resultStr = String.format(textTpl, new Object[] {
				toTextMessage.getFromUserName(), toTextMessage.getToUserName(),
				toTextMessage.getCreateTime(), toTextMessage.getMsgType(),
				toTextMessage.getContent() });
		return resultStr;
	}

	public static String OutFormatMsg(ToMusicMessage toMusicMessage) {
		String textTpl = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><FromUserName><![CDATA[%2$s]]></FromUserName><CreateTime>%3$s</CreateTime><MsgType><![CDATA[%4$s]]></MsgType><Music><Title><![CDATA[%5$s]]></Title><Description><![CDATA[%6$s]]></Description><MusicUrl><![CDATA[%7$s]]></MusicUrl><HQMusicUrl><![CDATA[%8$s]]></HQMusicUrl></Music><FuncFlag>0</FuncFlag></xml>";

		String resultStr = String.format(textTpl, new Object[] {
				toMusicMessage.getFromUserName(),
				toMusicMessage.getToUserName(),
				toMusicMessage.getMsgType(), toMusicMessage.getTitle(),
				toMusicMessage.getDescription(), toMusicMessage.getMusicUrl(),
				toMusicMessage.gethQMusicUrl() });

		return resultStr;
	}

	
	public static String OutFormatMsg(ToNewsMessage toNewsMessage) {
		TextImageMessage[] articles = toNewsMessage.getArticles();
		StringBuilder sb = new StringBuilder();
		int j = articles.length;
		for (int i = 0; i < j; ++i) {

			TextImageMessage imageMessage = articles[i];

			sb.append("<item>");
			sb.append("<Title><![CDATA[" + imageMessage.getTitle()
					+ "]]></Title>");
			sb.append("<Description><![CDATA[" + imageMessage.getDescription()
					+ "]]></Description>");
			sb.append("<PicUrl><![CDATA[" + imageMessage.getPicUrl()
					+ "]]></PicUrl>");
			sb.append("<Url><![CDATA[" + imageMessage.getUrl() + "]]></Url>");
			sb.append("");
			sb.append("</item>");
		}
		String textTpl = "<xml><ToUserName><![CDATA[%1$s]]></ToUserName><FromUserName><![CDATA[%2$s]]></FromUserName><CreateTime>%3$s</CreateTime><MsgType><![CDATA[%4$s]]></MsgType><ArticleCount>%5$s</ArticleCount><Articles>%6$s</Articles><FuncFlag>0</FuncFlag></xml>";

		String resultStr = String.format(textTpl,
				new Object[] { toNewsMessage.getFromUserName(),
						toNewsMessage.getToUserName(),
						toNewsMessage.getCreateTime(),
						toNewsMessage.getMsgType(),
						Integer.valueOf(toNewsMessage.getArticleCount()),
						sb.toString() });

		return resultStr;
	}

	public static String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; ++i)
			bf.append(arr[i]);

		return bf.toString();
	}

	public static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception md) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; ++i) {
			if ((bytes[i] & 0xFF) < 16)
				buf.append("0");

			buf.append(Long.toString(bytes[i] & 0xFF, 16));
		}
		return buf.toString().toUpperCase();
	}

	public static Date StrToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static double getDistatce(double lat1, double lat2, double lon1,
			double lon2) {
		double R = 6371.0D;
		double distance = 0D;
		double dLat = (lat2 - lat1) * 3.1415926535897931D / 180.0D;
		double dLon = (lon2 - lon1) * 3.1415926535897931D / 180.0D;
		double a = Math.sin(dLat / 2.0D) * Math.sin(dLat / 2.0D)
				+ Math.cos(lat1 * 3.1415926535897931D / 180.0D)
				* Math.cos(lat2 * 3.1415926535897931D / 180.0D)
				* Math.sin(dLon / 2.0D) * Math.sin(dLon / 2.0D);
		distance = 2.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a)) * R;
		return distance;
	}
}