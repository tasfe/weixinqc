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

public class WeiXin
{
  private final String token = "mzai";
  private HttpServletRequest request;
  private HttpServletResponse response;
  private PrintWriter out;

  public WeiXin(HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      request.setCharacterEncoding("utf-8");
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    response.setContentType("text/xml;charset=utf-8");
    this.request = request;
    this.response = response;
    if (checkSignature(this.request, this.token)){
        try {
    		this.out = this.response.getWriter();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        String echostr= request.getParameter("echostr");
        if((echostr == null) || (echostr.isEmpty())){
        	try{
	        	initAndSendMessage();
	        }catch(Exception ex){
	        	ex.printStackTrace();
	        }
        }else{
        	this.out.write(echostr);
	        
        }
    }
    
  }
  
  //接收消息并发送消息
	private void initAndSendMessage() throws IOException{

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

	        if (document != null){
	        	Element root = document.getRootElement();
		        String msgType = root.elementText("MsgType");
		        String touser = root.elementText("ToUserName");
		        String fromuser = root.elementText("FromUserName");
		        if ("text".equals(msgType))
		        {
		          String keyword = root.elementText("Content");
		          
		          //天气
		          if(keyword!=null&&!"".equals(keyword)&&keyword.length()>2){
		        	  String tianqi = keyword.substring(keyword.length()-2);
		        	  if("天气".equals(tianqi)){
		        		  String city = keyword.substring(0, keyword.length()-2).trim();
		        		  String weather = WeatherUtil.getWeatherStr(city);
		        		  ToTextMessage toTextMessage = new ToTextMessage();
		        		  toTextMessage.setContent(weather);
			              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
			              toTextMessage.setFromUserName(fromuser);
			              toTextMessage.setToUserName(touser);
			              SendMessage(toTextMessage);
			              return;
		        	  }
		          }
		          //翻译
		          if(keyword!=null&&!"".equals(keyword)&&keyword.length()>2){
		        	  String fanyi = keyword.substring(0,2);
		        	  if("翻译".equals(fanyi)){
		        		  String str = keyword.substring(2).trim();
		        		  String result = TransUtil.getTransStr(str);
		        		  ToTextMessage toTextMessage = new ToTextMessage();
		        		  toTextMessage.setContent(result);
			              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
			              toTextMessage.setFromUserName(fromuser);
			              toTextMessage.setToUserName(touser);
			              SendMessage(toTextMessage);
			              return;
		        	  }
		          }
		          //公交
		          if(keyword!=null&&!"".equals(keyword)&&keyword.length()>2){
		        	   String gongjiao = keyword.substring(0,2);
		        	   if("公交".equals(gongjiao)){
		        		   String str = keyword.substring(2);
		        		   String result = BusUtil.getBuslineStr(str);
			        		  ToTextMessage toTextMessage = new ToTextMessage();
			        		  toTextMessage.setContent(result);
				              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
				              toTextMessage.setFromUserName(fromuser);
				              toTextMessage.setToUserName(touser);
				              SendMessage(toTextMessage);
				              return;
		        		   
		        	   }
		          }
		          ToTextMessage toTextMessage = new ToTextMessage();
	              toTextMessage.setContent("目前平台功能如下：\n【1】查天气，如输入：济南天气\n【2】 查公交，如输入：公交济南 119\n【3】翻译，如输入：翻译I love you\n更多内容，敬请期待...");
	              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
	              toTextMessage.setFromUserName(root.elementText("FromUserName"));
	              toTextMessage.setToUserName(root.elementText("ToUserName"));
	              SendMessage(toTextMessage);
	              /*
		          if("1".equals(keyword)){
		        	  //构造一个图文消息
		        	  TextImageMessage textImageMessage = new TextImageMessage();
		        	  textImageMessage.setTitle("百度");
		        	  textImageMessage.setDescription("百度测试！");
		        	  textImageMessage.setUrl("http://www.baidu.com");
		        	  textImageMessage.setPicUrl("http://wenwen.soso.com/p/20110814/20110814175251-629691597.jpg");
		        	  TextImageMessage[] textImageMessages = {textImageMessage};
		        	  ToNewsMessage toNewsMessage = new ToNewsMessage(textImageMessages);
		        	  toNewsMessage.setFromUserName(root.elementText("FromUserName"));
		        	  toNewsMessage.setToUserName(root.elementText("ToUserName"));
		        	  toNewsMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
		              SendMessage(toNewsMessage);
		          }else
		    	  if("2".equals(keyword)){
		        	  ToTextMessage toTextMessage = new ToTextMessage();
		              toTextMessage.setContent("hello ,<a href='http://www.baidu.com?openid="+root.elementText("FromUserName")+"'>girl</a> !!!");
		              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
		              toTextMessage.setFromUserName(root.elementText("FromUserName"));
		              toTextMessage.setToUserName(root.elementText("ToUserName"));
		              SendMessage(toTextMessage);
		          }else{
		        	  ToTextMessage toTextMessage = new ToTextMessage();
		              toTextMessage.setContent("目前平台功能如下：\n【1】查天气，如输入：济南天气\n【2】 查公交，如输入：济南公交119\n【3】翻译，如输入：翻译I love you\n更多内容，敬请期待...");
		              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
		              toTextMessage.setFromUserName(root.elementText("FromUserName"));
		              toTextMessage.setToUserName(root.elementText("ToUserName"));
		              SendMessage(toTextMessage);
		          }*/
		        }
		        if ("event".equals(msgType))
		        {
		          if("subscribe".equals(root.elementText("Event"))){
		        	  ToTextMessage toTextMessage = new ToTextMessage();
		              toTextMessage.setContent("感谢您关注【mzai】\n微信号：mzai\n目前平台功能如下：\n【1】查天气，如输入：济南天气\n【2】 查公交，如输入：公交济南 119\n【3】翻译，如输入：翻译I love you\n更多内容，敬请期待...");
		              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
		              toTextMessage.setFromUserName(root.elementText("FromUserName"));
		              toTextMessage.setToUserName(root.elementText("ToUserName"));
		              SendMessage(toTextMessage);
		          }
	        }
	          

	        
	       
	        
//	        else if ("image".equals(msgType))
//	        {
//	          FromImageMessage imageMessage = new FromImageMessage();
//	          imageMessage.setPicUrl(root.elementText("image"));
//	          imageMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
//	          this.fromMessage = imageMessage;
//	        }
//	        else if ("location".equals(msgType))
//	        {
//	          FromLocationMessage locationMessage = new FromLocationMessage();
//	          locationMessage.setLocation_X(Double.parseDouble(root.elementText("Location_X")));
//	          locationMessage.setLocation_Y(Double.parseDouble(root.elementText("Location_Y")));
//	          locationMessage.setLabel(root.elementText("Label"));
//	          locationMessage.setScale(Integer.parseInt(root.elementText("Scale")));
//	          locationMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
//	          this.fromMessage = locationMessage;
//	        }
//	        else if ("link".equals(msgType))
//	        {
//	          FromLinkMessage linkMessage = new FromLinkMessage();
//	          linkMessage.setTitle(root.elementText("Title"));
//	          linkMessage.setDescription(root.elementText("description"));
//	          linkMessage.setUrl(root.elementText("url"));
//	          linkMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
//	          this.fromMessage = linkMessage;
//	        }
//	        else 
	        }
	      }
	}
  

  //发送消息
  public void SendMessage(ToMessage msg)
    throws IOException
  {
	    out.write(msg.initMsgStr());
	    out.flush();
	    out.close();
  }
  
//  public void SendMessage(ToMusicMessage toMusicMessage)
//    throws IOException
//  {
//    PrintWriter out = this.response.getWriter();
//    if ((this.echostr == null) || (this.echostr.isEmpty())) {
//      if (toMusicMessage != null) {
//        out.write(WeixinUtil.OutFormatMsg(toMusicMessage));
//      }
//
//    }
//    else if (checkSignature(this.request, this.token)) {
//      out.write(this.echostr);
//    }
//    else {
//      out.write("error");
//    }
//
//    out.flush();
//    out.close();
//  }

  

  //验证请求来源于微信服务器
  boolean checkSignature(HttpServletRequest request, String token)
  {
    String signature = request.getParameter("signature")==null?"":request.getParameter("signature");
    String timestamp = request.getParameter("timestamp")==null?"":request.getParameter("timestamp");
    String nonce = request.getParameter("nonce")==null?"":request.getParameter("nonce");
    if("".equals(signature) || "".equals(timestamp)||"".equals(nonce)){
    	return false;
    }
    String[] tmpArr = { token, timestamp, nonce };
    Arrays.sort(tmpArr);
    String tmpStr = WeixinUtil.ArrayToString(tmpArr);
    tmpStr = WeixinUtil.SHA1Encode(tmpStr);

    return (tmpStr.equalsIgnoreCase(signature));
  }
}