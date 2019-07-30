package org.pasedb.client;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

import org.jsoup.Connection;
//import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

import java.math.BigDecimal;

public class CLI {
	private final int MAX_XY = 200;

	
	
// (maybe...)	https://tersesystems.com/blog/2014/03/23/fixing-hostname-verification/
//	javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException: No subject alternative DNS name matching thehill.com found.
//	https://thehill.com/opinion/civil-rights/451289-removing-children-from-their-parents-doesnt-just-happen-at-the-border
//	https://www.fatherly.com/health-science/divorce-joint-custody-makes-men-better-fathers/
//	https://www.liveabout.com/signs-of-parental-alienation-syndrome-1103082
	
//	private final String pasedburl = "https://www.abc27.com/investigators/investigations/parental-alienation-is-your-child-being-brainwashed-to-not-like-you/";
	
	private final String pasedburl = "https://www.youtube.com/watch?v=fVt1po4OPIA";
	private PaseDBLinkItem ogi = new PaseDBLinkItem();
	
	public CLI() {
		try {
			
//			
//			  OpenGraph testPage = new OpenGraph(pasedburl, true);
//			  String title = testPage.getContent("title");
//			  String type = testPage.getContent("type");
//			  System.out.println("Title: " + title);
//			  System.out.println("Type: " + type);
//			  System.out.println(testPage.getContent("description"));
//			  System.out.println(testPage.getContent("image"));
			
			PaseDBLinkItem ogi = fetchOGMetaData(pasedburl,"comment", new ArrayList<Integer>(),-1);
//			System.out.println(ogi.getTitle());
			
			System.out.println(ogi.toString());
			
//			parsePageHeaderInfo(pasedburl);
//			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public PaseDBLinkItem fetchOGMetaData(String url, String comment, ArrayList<Integer> tags, int userID) throws Exception{
	    ogi.setUrl(url);
		String title = url;
	    String desc = "unavailable";
	    String imageUrl = null;
	    
//		System.out.println(System.getProperty("label"));
		
		try {
			Document document = Jsoup.connect(url).get();
		    Elements metaOgTitle = document.select("meta[property=og:title]");
		    if (metaOgTitle!=null) {
		        title = metaOgTitle.attr("content");
		    }
		    if (title == null || title.length() < 3){
		        title = document.title();
		    }
		    if (title == null || title.length() < 3){
		    	title = url;
		    }
		    
		    Elements metaOgDesc = document.select("meta[property=og:description]");
		    if (metaOgTitle!=null) {
		    	desc = metaOgDesc.attr("content");
		    }else {
		    	desc = "No description available";
		    }
		    Elements metaOgImage = document.select("meta[property=og:image]");  
		    if (metaOgImage!=null) {
		    	imageUrl = metaOgImage.attr("content");
		    	setImageDimensions(imageUrl);
		    }

//		    URL:  max length than 256 characters
//		    System.out.println(">> URL: " + url);
//		    title max length than 100 characters
//		    System.out.println(">> Title: " + title);
//		    description max length than 200 characters
//		    System.out.println(">> Description: " + desc);
//		    Image url max length than 256 characters
//		    System.out.println(">> Image: " + imageUrl);
//		    System.out.println(">> Comment: " + comment);
//		    System.out.println(">> UserID: " + userID);
		    
		    ogi.setTitle(title);
		    ogi.setDescription(desc);
		}catch(javax.net.ssl.SSLHandshakeException ce) {
			System.out.println("[#error] ---------- JSOUP FAILED TO CONNECT / TRYING OPENGRAPH TOOLSET ----------------");
			ce.printStackTrace();
			tryOpenGraphToolset();
		}
	    
	    ogi.setComment(comment);
	    ogi.setUserID(userID);
	    ogi.setTags(tags);
	    	    
//	    try{	    	
//	    	persist(ogi);
//	    	
//	    }catch(Exception ex){
//	    	System.out.println("FAILED TO INSERT RECORD");
//	    	ex.printStackTrace();
//	    }
	    
	    return ogi;
	}

	
	private void tryOpenGraphToolset() throws Exception{
		  OpenGraph testPage = new OpenGraph(pasedburl, true);
		  ogi.setTitle(testPage.getContent("title"));
		  ogi.setDescription(testPage.getContent("description"));
		  String imageUrl = testPage.getContent("image");
		  if (imageUrl != null) {
			  setImageDimensions(imageUrl);
		  }
//		  System.out.println("Title: " + title);
//		  System.out.println("desc: " + desc);
//		  System.out.println("imageUrl: " + imageUrl);
	}
	
	public void setImageDimensions(String imageUrl) throws Exception {
//    	System.out.println("imageUrl: " + imageUrl);
//    	System.out.println("length: " + imageUrl.trim().length());
    	if(imageUrl != null && imageUrl.trim().length() > 4){
				URL iurl=new URL(imageUrl);
//					BufferedImage image = ImageIO.read(iurl);					
				HttpURLConnection connection = (HttpURLConnection)iurl.openConnection();
				connection.setRequestProperty(
				    "User-Agent",
				    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
				BufferedImage image = ImageIO.read(connection.getInputStream());
				int height = image.getHeight();
				int width = image.getWidth();
//				System.out.println("Image Height : "+ height);
//				System.out.println("Image Width : "+ width);
				float pct = setPct(height,width);
				int display_height = Math.round(height * pct);
				int display_width = Math.round(width * pct);
//				System.out.println("Display Height : "+ display_height);
//				System.out.println("Display Width : "+ display_width);
				ogi.setImgurl(imageUrl);
				ogi.setDisplayHeight(display_height);
				ogi.setDisplayWidth(display_width);
				// add the code to make the image smaller here -- revision 2, no need to work on this now!
    	}else {
    		throw new NullPointerException("ImageURL is invalid");
    	}

	}
	
	public static String parsePageHeaderInfo(String urlStr) throws Exception {

	    StringBuilder sb = new StringBuilder();
	    Connection con = Jsoup.connect(urlStr);

	    /* this browseragant thing is important to trick servers into sending us the LARGEST versions of the images */
//	    con.userAgent(Constants.BROWSER_USER_AGENT);
	    con.userAgent("Mozilla/5.0 (iPad; U; CPU OS 3_2_1 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Mobile/7B405");
	    Document doc = con.get();
	    
	    String text = null;
	    Elements metaOgTitle = doc.select("meta[property=og:title]");
	    if (metaOgTitle!=null) {
	        text = metaOgTitle.attr("content");
	    }
	    else {
	        text = doc.title();
	    }

	    String imageUrl = null;
	    Elements metaOgImage = doc.select("meta[property=og:image]");
	    if (metaOgImage!=null) {
	        imageUrl = metaOgImage.attr("content");
	    }

	    if (imageUrl!=null) {
	        sb.append("<img src='");
	        sb.append(imageUrl);
	        sb.append("' align='left' hspace='12' vspace='12' width='150px'>");
	    }

	    if (text!=null) {
	        sb.append(text);
	    }

	    return sb.toString();       
	}
	
	    	private float setPct(int height, int width) {
	    		double max = new Double(height).doubleValue();
	    		if (width > height) max = new Double(width).doubleValue();
	    		if (max <= MAX_XY) return 1;
	    		double val = MAX_XY / max; 
	    	    BigDecimal bd = new BigDecimal(Double.toString(val));
	    	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);       
//	    		System.out.println("width: " + width);
//	    		System.out.println("height: " + height);
//	    		System.out.println("max: " + max);
//	    		System.out.println("MAX_XY: " + MAX_XY);m 
//	    		System.out.println("val: " + val);
//	    		System.out.println("bd: " + bd.floatValue());
	    	    return bd.floatValue();
	    	}
	    	
	
	public static void main(String[] args) {
		new CLI();
	}
}
