<%@page import="util.*" contentType="text/plain;charset=utf-8"%><%@page
	import="org.json.*"%><%@page import="java.net.*"%><%@page
	import="java.io.*"%><%
	
	boolean jsonP=false;
	String result="";
	
	RankParser json = new RankParser();
	
	String cb=request.getParameter("callback");
	if(cb!=null){
		jsonP=true;
		response.setContentType("text/javascript");
	}else{
		response.setContentType("application/x-json");
	}
	
	if(jsonP){
		result=result+cb+"(";
	}
	result=result+json.getJsonResult();
	if(jsonP){
		result=result+");";
	}
%><%=result%>