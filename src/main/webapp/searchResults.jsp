<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.*"%>
<jsp:useBean id="oWebClient" class="ca.on.conestogac.WebClient" scope="request" />
<%

String sTerm = request.getParameter("term");
String sLocation = request.getParameter("location");
String sUrl = "http://www.wowjobs.ca/wowrss.aspx?q=%s&l=%s&s=r&sr=25&t=30&f=r&e=&si=A&Dup=H";
oWebClient.downloadXMLasJSON(String.format(sUrl, URLEncoder.encode(sTerm, "UTF-8"), 
		URLEncoder.encode(sLocation, "UTF-8")), out);



%>
