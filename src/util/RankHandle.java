package util;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RankHandle extends DefaultHandler {
	boolean flagK = false;
	boolean flagS = false;
	boolean flagV = false;

	RankPostDTO post;
	ArrayList<RankPostDTO> posts = new ArrayList<RankPostDTO>();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		if (localName.equals("K")) {
			post = new RankPostDTO();
			flagK = true;
		} else if (localName.equals("S")) {
			flagS = true;
		} else if (localName.equals("V")) {
			flagV = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);

		if (flagK) {
			// k를 만나면 setK, K에다가 ch와 시작부분과 length를 지정한다.
			post.setK(new String(ch, start, length));
		} else if (flagS) {
			// S를 만나면 setK, S에다가 ch와 시작부분과 length를 지정한다.
			post.setS(new String(ch, start, length));
		} else if (flagV) {
			post.setV(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if (localName.equals("K")) {
			flagK = false; // 다음 element를 위해 false로 set
		} else if (localName.equals("S")) {
			flagS = false; // 다음 element를 위해 false로 set
		} else if (localName.equals("V")) {
			flagV = false; // 다음 element를 위해 false로 set
			posts.add(post);
		}
	}
}
