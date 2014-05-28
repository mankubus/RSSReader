package com.test.rssreader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RSSItem {
	  private String title;
	  private String description;
	  private String link;


	  /** Constructor */
	  public RSSItem(String _title, String _description, String _link) {
	    this.title = _title;
	    this.description = _description;
	    this.link = _link;
	  }

	  public String getTitle(){
	    return this.title;
	  }

	  public String getLink(){
	    return this.link;
	  }

	  public String getDescription(){
	    return this.description;
	  }
	  
	  
	  @Override
	  public String toString() {
	    String result = getTitle();
	    return result;
	  }

	  /** Get RSS items from selected site */
	  public static ArrayList<RSSItem> getRssItems(String feedUrl) {
	    
		ArrayList<RSSItem> rssItems = new ArrayList<RSSItem>();

	    try {
	      URL url = new URL(feedUrl);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();


	      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        InputStream is = conn.getInputStream();
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();

	        //XML Parsing
	        Document document = db.parse(is);
	        Element element = document.getDocumentElement();
	        
	        //RSS items (news) to list
	        NodeList nodeList = element.getElementsByTagName("item");

	        if (nodeList.getLength() > 0) {
	          for (int i = 0; i < nodeList.getLength(); i++) {
	        	  Element entry = (Element) nodeList.item(i);
	        	  
	        	  //Get title, link and description (base fields)
	        	  Element _titleE = (Element) entry.getElementsByTagName("title").item(0);
	        	  Element _linkE = (Element) entry.getElementsByTagName("link").item(0);
	        	  Element _descriptionE = (Element) entry.getElementsByTagName("description").item(0);
	        	  
	        	  //Get string values of base fields
	        	  String _title = _titleE.getFirstChild().getNodeValue();
	        	  String _link = _linkE.getFirstChild().getNodeValue();
	        	  String _description = _descriptionE.getFirstChild().getNodeValue();

	        	  //create RSSItem and add it to the array
	        	  RSSItem rssItem = new RSSItem(_title, _description, _link);
	        	  rssItems.add(rssItem);
	          } //for
	        } // if (nodeList.getLength() > 0)
	      } /** if (conn.getResponseCode() == Ht... */
	    } //try
	    catch (Exception e) {
	      e.printStackTrace();
	    }

	    return rssItems;
	  } //getRssItems
}
