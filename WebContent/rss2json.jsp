<%@page import="util.RSSProxy" contentType="text/plain;charset=euc-kr"
%><%@page import="org.json.*"%><%@page import="java.net.*"
%><%@page import="java.io.*"
%><%
	/**
	 *	xml �������� �����ϴ� RSS�� json ���ڿ��� �Ľ��ϴ� ����Դϴ�.
	 */

	//url �ڿ� �ٴ� query string�� key�� addr�� �׸��� ���� �о�ɴϴ�. ���⼭ addr�� ������ rss �ּ��Դϴ�.
	String addr = request.getParameter("addr");
	if (addr == null) {
		addr = "http://boribab.tistory.com/rss";
	}
	/*String qs = request.getQueryString();
	if (qs == null) {
		qs = "http://boribab.tistory.com/rss";
	}*/

	//������ rss �ּҿ� �ִ� ������ InputStream ��Ʈ�� �� �о�ͼ� ���� feed�� �����մϴ�.
	String feed = RSSProxy.getCachedXML(addr, "utf-8");
	JSONObject obj = XML.toJSONObject(feed);
%><%=obj%>