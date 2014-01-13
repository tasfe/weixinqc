<%@ page language="java" import="java.util.*,java.net.URL,org.qcun.wx.message.WeiXin" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
WeiXin wx = new WeiXin(request,response,"mzai");
wx.SendMessage();
%>