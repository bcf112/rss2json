package util;

import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class RankParser {
	HttpClient client;
	String jsonResult="";

	public String getJsonResult() {
		return jsonResult;
	}

	public RankParser() {
		client = new DefaultHttpClient(); // 클라이언트의 인스턴스 생성
		// 네이버가 제공해주는 페이지 접근
		// xml문서 get
		// sax parser를 통해서 파싱하여 textView에 뿌린다.
		getRank();
	}

	private void getRank() {
		// 네이버에서 받은 키를 통해서 http 에 접근한다. URL를 넣어준다.
		String url = "http://openapi.naver.com/search?key=907ed5cd8ab441dc61ffb0016280e906&query=nexearch&target=rank";
		HttpGet getMethod = new HttpGet(url);

		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseData = client.execute(getMethod, responseHandler);
			// client.execute() 메서드를 통해서 네이버가 제공해주는 XML에 대한 위치 정보를 참조
			// 나온 결과값을 parseXmlSax에 넣어준다. <> 구문이 섞여 있는 상태로 모든 Stringdl
			// responseData에 저장되어 있다. parseXmlSax는 이를 받았다.
			parseXmlSax(responseData);
		} catch (Exception e) {
		}
	}

	private void parseXmlSax(String responseData) {
		try {
			// SAXparser를 만들어주는 factory에 newInstance()를 호출
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sp = factory.newSAXParser();

			// 실제로 xml을 읽어들이는 역할을 하는 xmlreader 타입의 xr로 읽어들인 xml 참조
			XMLReader xr = sp.getXMLReader();
			// RankHandle이라는 것을 선언해줌과 동시에 xr에 set해준다.
			xr.setContentHandler(new RankHandle());
			xr.parse(new InputSource(new StringReader(responseData)));

			RankHandle rh = (RankHandle) xr.getContentHandler();
			// RankHandle에 있는 rh의 posts 리스트를 arraylist에 담는다. 이곳에서 10번을 돌아 실시간 검색
			// 10개를 만들어 내는 것이다.
			ArrayList<RankPostDTO> list = rh.getPosts();
			makeJson(list);
		} catch (Exception e) {
		}
	}
	
	private void makeJson(ArrayList<RankPostDTO> list) {
		String rankStr = "";
		
		/*jsonResult=jsonResult+"{\"responseData\":{\"rank\":[";
		for (int i = 0; i < list.size(); i++) {
			RankPostDTO post = list.get(i);
			rankStr=rankStr+"{";
			rankStr = rankStr + "\"keyword\":\"" + post.getK() + "\"," + "\"placing\":\"" + post.getS() + "\"," + "\"value\":\"" + post.getV() + "\"" ;
			if(i==list.size()-1){
				rankStr=rankStr+"}";
			}else{
				rankStr=rankStr+"},";
			}
		}
		jsonResult=jsonResult+rankStr+"]}}";*/
		
		jsonResult=jsonResult+"[";
		for (int i = 0; i < list.size(); i++) {
			RankPostDTO post = list.get(i);
			rankStr=rankStr+"{";
			rankStr = rankStr + "\"keyword\":\"" + post.getK() + "\"," + "\"placing\":\"" + post.getS() + "\"," + "\"value\":\"" + post.getV() + "\"" ;
			if(i==list.size()-1){
				rankStr=rankStr+"}";
			}else{
				rankStr=rankStr+"},";
			}
		}
		jsonResult=jsonResult+rankStr+"]";
	}
}
