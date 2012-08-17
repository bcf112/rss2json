<%@page import="util.*" contentType="text/plain;charset=utf-8"
%><%@page import="org.json.*"%><%@page import="java.net.*"
%><%@page import="java.io.*"
%><%
	String addr = request.getParameter("addr");
	if (addr == null) {
		addr = "http://boribab.tistory.com/rss";
	}
	
	
	HttpClient client = new DefaultHttpClient();

	String feed = RSSProxy.getCachedXML(addr, "utf-8");
	JSONObject obj = XML.toJSONObject(feed);
%><%=obj%>