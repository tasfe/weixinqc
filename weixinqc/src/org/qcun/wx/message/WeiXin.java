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
import org.qcun.wx.util.WeixinUtil;

public class WeiXin
{
  private HttpServletRequest request;
  private HttpServletResponse response;
  private FromMessage fromMessage;
  private String token;
  private String echostr;

  public WeiXin(HttpServletRequest request, HttpServletResponse response, String token)
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
    this.token = token;

    this.echostr = request.getParameter("echostr");
    try{
    	fromatMessage();
    }catch(Exception ex){
    	ex.printStackTrace();
    }
  }

  public FromMessage getFromMessage()
  {
    return this.fromMessage;
  }

  public void SendMessage()
    throws IOException
  {
    PrintWriter out = this.response.getWriter();
    if ((this.echostr == null) || (this.echostr.isEmpty())) {
      if (this.fromMessage != null) {
        System.out.println("message 正常....");
      }

    }
    else if (checkSignature(this.request, this.token)) {
      out.write(this.echostr);
    }
    else {
      out.write("error");
    }

    out.flush();
    out.close();
  }

  public void SendMessage(ToTextMessage toTextMessage)
    throws IOException
  {
    PrintWriter out = this.response.getWriter();
    if ((this.echostr == null) || (this.echostr.isEmpty())) {
      if (toTextMessage != null) {
        out.write(WeixinUtil.OutFormatMsg(toTextMessage));
      }

    }
    else if (checkSignature(this.request, this.token)) {
      out.write(this.echostr);
    }
    else {
      out.write("error");
    }

    out.flush();
    out.close();
  }

  public void SendMessage(ToMusicMessage toMusicMessage)
    throws IOException
  {
    PrintWriter out = this.response.getWriter();
    if ((this.echostr == null) || (this.echostr.isEmpty())) {
      if (toMusicMessage != null) {
        out.write(WeixinUtil.OutFormatMsg(toMusicMessage));
      }

    }
    else if (checkSignature(this.request, this.token)) {
      out.write(this.echostr);
    }
    else {
      out.write("error");
    }

    out.flush();
    out.close();
  }

  public void SendMessage(ToNewsMessage toNewsMessage)
    throws IOException
  {
    PrintWriter out = this.response.getWriter();
    if ((this.echostr == null) || (this.echostr.isEmpty())) {
      if (toNewsMessage != null) {
        out.write(WeixinUtil.OutFormatMsg(toNewsMessage));
      }

    }
    else if (checkSignature(this.request, this.token)) {
      out.write(this.echostr);
    }
    else {
      out.write("error");
    }

    out.flush();
    out.close();
  }

  private void fromatMessage() throws IOException
  {
    if ((this.echostr == null) || (this.echostr.isEmpty()))
    {
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
          System.out.println("**********微信服务器***********");
          System.out.println(postStr);
        } catch (Exception e) {
          e.printStackTrace();
        }

        if (document == null)
          this.fromMessage = null;

        Element root = document.getRootElement();
        String msgType = root.elementText("MsgType");
       
        if ("text".equals(msgType))
        {
          String keyword = root.elementText("Content");
          FromTextMessage textMessage = new FromTextMessage();
          textMessage.setContent(root.elementText("Content"));
          textMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
          if("1".equals(keyword)){
        	  ToTextMessage toTextMessage = new ToTextMessage();
              toTextMessage.setContent("hello ,boy !!!");
              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
              toTextMessage.setFromUserName(root.elementText("FromUserName"));
              toTextMessage.setToUserName(root.elementText("ToUserName"));
              toTextMessage.setOnTime(String.valueOf((new Date()).getTime()/1000));
              SendMessage(toTextMessage);
          }else
    	  if("2".equals(keyword)){
        	  ToTextMessage toTextMessage = new ToTextMessage();
              toTextMessage.setContent("hello ,<a href='http://www.baidu.com'>girl</a> !!!");
              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
              toTextMessage.setFromUserName(root.elementText("FromUserName"));
              toTextMessage.setToUserName(root.elementText("ToUserName"));
              toTextMessage.setOnTime(String.valueOf((new Date()).getTime()/1000));
              SendMessage(toTextMessage);
          }else
    	  if("3".equals(keyword)){
        	  ToTextMessage toTextMessage = new ToTextMessage();
              toTextMessage.setContent("hello ,X !!!");
              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
              toTextMessage.setFromUserName(root.elementText("FromUserName"));
              toTextMessage.setToUserName(root.elementText("ToUserName"));
              toTextMessage.setOnTime(String.valueOf((new Date()).getTime()/1000));
              SendMessage(toTextMessage);
          }else{
        	  ToTextMessage toTextMessage = new ToTextMessage();
              toTextMessage.setContent("请输入：\n1.男生信息\n2.女生信息\n3.X信息");
              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
              toTextMessage.setFromUserName(root.elementText("FromUserName"));
              toTextMessage.setToUserName(root.elementText("ToUserName"));
              toTextMessage.setOnTime(String.valueOf((new Date()).getTime()/1000));
              SendMessage(toTextMessage);
          }
          
          this.fromMessage = textMessage;
        }
        else if ("image".equals(msgType))
        {
          FromImageMessage imageMessage = new FromImageMessage();
          imageMessage.setPicUrl(root.elementText("image"));
          imageMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
          this.fromMessage = imageMessage;
        }
        else if ("location".equals(msgType))
        {
          FromLocationMessage locationMessage = new FromLocationMessage();
          locationMessage.setLocation_X(Double.parseDouble(root.elementText("Location_X")));
          locationMessage.setLocation_Y(Double.parseDouble(root.elementText("Location_Y")));
          locationMessage.setLabel(root.elementText("Label"));
          locationMessage.setScale(Integer.parseInt(root.elementText("Scale")));
          locationMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
          this.fromMessage = locationMessage;
        }
        else if ("link".equals(msgType))
        {
          FromLinkMessage linkMessage = new FromLinkMessage();
          linkMessage.setTitle(root.elementText("Title"));
          linkMessage.setDescription(root.elementText("description"));
          linkMessage.setUrl(root.elementText("url"));
          linkMessage.setMsgId(Double.parseDouble(root.elementTextTrim("MsgId")));
          this.fromMessage = linkMessage;
        }
        else if ("event".equals(msgType))
        {
          FromEventMessage eventMessage = new FromEventMessage();
          eventMessage.setEvent(root.elementText("Event"));
          eventMessage.setEventKey(root.elementText("EventKey"));
          if("subscribe".equals(root.elementText("Event"))){
        	  ToTextMessage toTextMessage = new ToTextMessage();
              toTextMessage.setContent("谢谢关注!!!");
              toTextMessage.setCreateTime(String.valueOf((new Date()).getTime()/1000));
              toTextMessage.setFromUserName(root.elementText("FromUserName"));
              toTextMessage.setToUserName(root.elementText("ToUserName"));
              toTextMessage.setOnTime(String.valueOf((new Date()).getTime()/1000));
              SendMessage(toTextMessage);
          }
          this.fromMessage = eventMessage;
        }
        this.fromMessage.setFromUserName(root.elementText("FromUserName"));
        this.fromMessage.setToUserName(root.elementText("ToUserName"));
        this.fromMessage.setCreateTime(root.elementText("CreateTime"));
        this.fromMessage.setMsgType(msgType);
        this.fromMessage.setCreateTime(root.elementText("CreateTime"));
      }
    }
    else {
      this.fromMessage = null;
    }
  }

  boolean checkSignature(HttpServletRequest request, String token)
  {
    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");
    String[] tmpArr = { token, timestamp, nonce };
    Arrays.sort(tmpArr);
    String tmpStr = WeixinUtil.ArrayToString(tmpArr);
    tmpStr = WeixinUtil.SHA1Encode(tmpStr);

    return (tmpStr.equalsIgnoreCase(signature));
  }
}