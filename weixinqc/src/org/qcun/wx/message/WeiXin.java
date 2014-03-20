package org.qcun.wx.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.qcun.wx.util.BusUtil;
import org.qcun.wx.util.TransUtil;
import org.qcun.wx.util.WeatherUtil;
import org.qcun.wx.util.WeixinUtil;

public class WeiXin {
	private final String token = "mzai";
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;

	/**
	 * 构造方法
	 * 
	 * @param request
	 * @param response
	 */
	public WeiXin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/xml;charset=utf-8");
		this.request = request;
		this.response = response;
		if (checkSignature(this.request, this.token)) {
			try {
				this.out = this.response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String echostr = request.getParameter("echostr");
			if ((echostr == null) || (echostr.isEmpty())) {
				try {
					initAndSendMessage();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				this.out.write(echostr);
			}
		}
	}

	/**
	 * 接收消息并发送消息
	 * 
	 * @throws IOException
	 */
	private void initAndSendMessage() throws IOException {
		String postStr = null;
		try {
			postStr = IOUtils.toString(this.request.getInputStream(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((postStr != null) && (!(postStr.isEmpty()))) {
			Document document = null;
			try {
				document = DocumentHelper.parseText(postStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (document != null) {
				String rStr = dealDocument(document);
				if (!rStr.equals("") ){
					SendMessage(rStr);
				}
			}
		}
	}

	/**
	 * 处理接收到的XML信息
	 * 
	 * @param document
	 * @return ToMessage
	 * @throws IOException
	 */
	private String dealDocument(Document document) throws IOException {
		Element root = document.getRootElement();
		String touser = root.elementText("ToUserName");
		String fromuser = root.elementText("FromUserName");
		String msgType = root.elementText("MsgType");
		String createtime = root.elementText("CreateTime");
		String msgId = root.elementText("MsgId");
		FromMessage fromMessage = new FromMessage(touser, fromuser, msgType,
				createtime,msgId);
		if ("text".equals(msgType)) {
			return new TextMessage(fromMessage,root.elementText("Content")).deal();
		}
		if("location".equals(msgType)){
			return new LocationMessage(fromMessage,new Location(root.elementText("Location_X"),root.elementText("Location_Y"),root.elementText("Scale"),root.elementText("Label"))).deal();
		}
		return "";
	}
	/**
	 * 发送消息
	 * 
	 * @param toMessage
	 * @throws IOException
	 */
	private void SendMessage(String msgStr) throws IOException {
		out.write(msgStr);
		out.flush();
		out.close();
	}

	/**
	 * 验证请求来源于微信服务器
	 * 
	 * @param request
	 * @param token
	 * @return
	 */
	private boolean checkSignature(HttpServletRequest request, String token) {
		String signature = request.getParameter("signature") == null ? ""
				: request.getParameter("signature");
		String timestamp = request.getParameter("timestamp") == null ? ""
				: request.getParameter("timestamp");
		String nonce = request.getParameter("nonce") == null ? "" : request
				.getParameter("nonce");
		if ("".equals(signature) || "".equals(timestamp) || "".equals(nonce)) {
			return false;
		}
		String[] tmpArr = { token, timestamp, nonce };
		Arrays.sort(tmpArr);
		String tmpStr = WeixinUtil.ArrayToString(tmpArr);
		tmpStr = WeixinUtil.SHA1Encode(tmpStr);
		return (tmpStr.equalsIgnoreCase(signature));
	}
}