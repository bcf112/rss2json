<%@page import="util.RSSProxy" contentType="text/plain;charset=euc-kr"
%><%@page import="org.json.*"%><%@page import="java.net.*"
%><%@page import="java.io.*"
%><%
	/**
	 *	xml 형식으로 제공하는 RSS를 json 문자열로 파싱하는 기능입니다.
	 */

	//url 뒤에 붙는 query string중 key가 addr인 항목의 값을 읽어옵니다. 여기서 addr은 가져올 rss 주소입니다.
	String addr = request.getParameter("addr");
	if (addr == null) {
		addr = "http://boribab.tistory.com/rss";
	}
	/*String qs = request.getQueryString();
	if (qs == null) {
		qs = "http://boribab.tistory.com/rss";
	}*/

	//제공한 rss 주소에 있는 내용을 InputStream 세트로 다 읽어와서 변수 feed에 저장합니다.
	String feed = RSSProxy.getCachedXML(addr, "utf-8");
	JSONObject obj = XML.toJSONObject(feed);
%><%=obj%>