package org.pasedb.pasedbui;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
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
import java.util.Date;
import java.util.List;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

import java.math.BigDecimal;

public class AddNewLink {

	private final int MAX_XY = 200;
	
	public void addNewLink(HttpServletRequest request) {
		MongoClient mongoClient = null;
		try {
			String remoteIP = request.getRemoteAddr();
			// mongoClient = new MongoClient();
			// mongoClient = new MongoClient("gorkly", 27017);
			// mongoClient = new MongoClient("gorkly");
			mongoClient = new MongoClient("db");
			// or, to connect to a replica set, supply a seed list of members
			// MongoClient mongoClient = new MongoClient(Arrays.asList(new
			// ServerAddress("localhost", 27017),
			// new ServerAddress("localhost", 27018),
			// new ServerAddress("localhost", 27019)));
			
			DB db = mongoClient.getDB("pasedb");
			DBCollection coll = db.getCollection("links");
			List<BasicDBObject> tags = new ArrayList<BasicDBObject>();
			Multiset<String> hashTags = TreeMultiset.create();
			hashTags.add("#fake");
			hashTags.add("#tags");
			// tags.add(new DB)
			BasicDBObject newURL = new BasicDBObject("ip", remoteIP).append("user", "fake-user").append("timestamp",
					new Date());
			newURL.append("title", "fake-title").append("description", "fake-description").append("url", "fake-url")
					.append("tags", hashTags);

			// System.out.println("Data Display");
			coll.insert(newURL);
			DBCursor cursor = coll.find();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				// mongoClient.dropDatabase("test");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public LinkItem fetchOGMetaData(String url, String comment, int userID) throws Exception{
		LinkItem ogi = new LinkItem();
		Document document = Jsoup.connect(url).get();
	    String title = null;
	    Elements metaOgTitle = document.select("meta[property=og:title]");
	    if (metaOgTitle!=null) {
	        title = metaOgTitle.attr("content");
	    }
	    else {
	        title = document.title();
	    }
	    String desc = null;
	    Elements metaOgDesc = document.select("meta[property=og:description]");
	    if (metaOgTitle!=null) {
	    	desc = metaOgDesc.attr("content");
	    }else {
	    	desc = "missing og:description";
	    }
	    
	    String imageUrl = null;
	    Elements metaOgImage = document.select("meta[property=og:image]");
	    if (metaOgImage!=null) {
	    	imageUrl = metaOgImage.attr("content");
//	    	System.out.println("imageUrl: " + imageUrl);
//	    	System.out.println("length: " + imageUrl.trim().length());
	    	if(imageUrl != null && imageUrl.trim().length() > 4){
		    	try{
					URL iurl=new URL(imageUrl);
					BufferedImage image = ImageIO.read(iurl);
					int height = image.getHeight();
					int width = image.getWidth();
					System.out.println("Image Height : "+ height);
					System.out.println("Image Width : "+ width);
					float pct = setPct(height,width);
					int display_height = Math.round(height * pct);
					int display_width = Math.round(width * pct);
					System.out.println("Display Height : "+ display_height);
					System.out.println("Display Width : "+ display_width);
					ogi.setImgurl(imageUrl);
					ogi.setDisplayHeight(display_height);
					ogi.setDisplayWidth(display_width);
					// add the code to make the image smaller here -- revision 2, no need to work on this now!
		    	}catch(java.net.MalformedURLException mu){
		    		mu.printStackTrace();
		    	}
	    	}
	    }
	    
//	    URL:  max length than 256 characters
	    System.out.println("URL: " + url);
//	    title max length than 100 characters
	    System.out.println("Title: " + title);
//	    description max length than 200 characters
	    System.out.println("Description: " + desc);
//	    Image url max length than 256 characters
	    System.out.println("Image: " + imageUrl);
	    System.out.println("comment: " + comment);
	    System.out.println("userID: " + userID);
	    
	    ogi.setUrl(url);
	    ogi.setImgurl(imageUrl);
	    ogi.setTitle(title);
	    ogi.setDescription(desc);
	    ogi.setComment(comment);
	    ogi.setUserID(userID);
	    	    
	    try{
	    	
	    	persist(ogi);
	    	
	    }catch(Exception ex){
	    	System.out.println("FAILED TO INSERT RECORD");
	    	ex.printStackTrace();
	    }
	    
	    return ogi;
	}
	
	private void persist(LinkItem ogi) throws Exception{
		MongoConnectionmanager connMan = new MongoConnectionmanager("db");
		MongoDatabase mongodb = connMan.getDatabase("pasedb");	
		try{
			mongodb.getCollection("links").insertOne(new org.bson.Document("url", ogi.getUrl())
					.append("title", ogi.getTitle())
					.append("desc", ogi.getDescription())
					.append("imageUrl", ogi.getImgurl())
					.append("display_height", ogi.getDisplayHeight())
					.append("display_width", ogi.getDisplayWidth())
					.append("comment", ogi.getComment())
					.append("userID", ogi.getUserID())
					.append("date", new Date()));
		}finally{
			if (connMan != null) connMan.closeConnection();
		}
	}
//	
	
	
	private float setPct(int height, int width) {
		double max = new Double(height).doubleValue();
		if (width > height) max = new Double(width).doubleValue();;
		if (max <= MAX_XY) return 1;
		double val = MAX_XY / max; 
	    BigDecimal bd = new BigDecimal(Double.toString(val));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);       
//		System.out.println("width: " + width);
//		System.out.println("height: " + height);
//		System.out.println("max: " + max);
//		System.out.println("MAX_XY: " + MAX_XY);m 
//		System.out.println("val: " + val);
//		System.out.println("bd: " + bd.floatValue());
	    return bd.floatValue();
	}
	
	
	
	
	
	
	
}
