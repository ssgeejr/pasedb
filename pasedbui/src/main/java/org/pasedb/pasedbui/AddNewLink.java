package org.pasedb.pasedbui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.*;

import java.util.ArrayList;
import java.math.BigDecimal;

public class AddNewLink {

	private final int MAX_XY = 200;
	private LinkItem ogi = new LinkItem();
	
	public LinkItem fetchOGMetaData(String url, String comment, ArrayList<Integer> tags, int userID) throws Exception{
	    ogi.setUrl(url);
		String title = url;
	    String desc = "unavailable";
	    String imageUrl = null;
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
		    ogi.setTitle(title);
		    ogi.setDescription(desc);
		}catch(javax.net.ssl.SSLHandshakeException ce) {
			System.out.println("[#error] ---------- JSOUP FAILED TO CONNECT / TRYING OPENGRAPH TOOLSET ----------------");
			ce.printStackTrace();
			tryOpenGraphToolset(url);
		}
	    
	    ogi.setComment(comment);
	    ogi.setUserID(userID);
	    ogi.setTags(tags);
	    	    
	    try{	    	
	    	persist(ogi);
	    }catch(Exception ex){
	    	System.out.println("FAILED TO INSERT RECORD");
	    	ex.printStackTrace();
	    }
	    
	    return ogi;
	}

	private void tryOpenGraphToolset(String pasedburl) throws Exception{
		  OpenGraph testPage = new OpenGraph(pasedburl, true);
		  ogi.setTitle(testPage.getContent("title"));
		  ogi.setDescription(testPage.getContent("description"));
		  String imageUrl = testPage.getContent("image");
		  if (imageUrl != null) {
			  setImageDimensions(imageUrl);
		  }
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
	
	private void persist(LinkItem ogi) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection pasedbconn=DriverManager.getConnection("jdbc:mysql://pasedb:3306/pasedb?user=pasedb&password=alienation"); 
		PreparedStatement newlink=pasedbconn.prepareStatement("insert into palink(title,url,description,imageurl,display_height,display_width,userid) values(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS); 
		PreparedStatement newtag=pasedbconn.prepareStatement("insert into tag(tag,palinkid) values(?,?)"); 
		int palinkid = -1;
		try{
			try{
				newlink.setString(1,ogi.getTitle());
				newlink.setString(2,ogi.getUrl());
				newlink.setString(3,ogi.getDescription());
				newlink.setString(4,ogi.getImgurl());
				newlink.setInt(5,ogi.getDisplayHeight());
				newlink.setInt(6,ogi.getDisplayWidth());
				newlink.setInt(7,ogi.getUserID());
				newlink.executeUpdate();
				ResultSet rs = newlink.getGeneratedKeys();  
				palinkid = rs.next() ? rs.getInt(1) : -1;
			}catch(Exception exa){
				exa.printStackTrace();
				throw exa;
			}
			
			try{
				ArrayList<Integer> tags = ogi.getTags();
				if (tags.size() > 0)
					for(Integer tag:tags){
						int itag = tag.intValue();
	    				System.out.println(itag);    				
						newtag.setInt(1,itag);
						newtag.setInt(2,palinkid);
						newtag.executeUpdate();
					}
			}catch(Exception exa){
				exa.printStackTrace();
			}
		}finally{
			if (pasedbconn != null) pasedbconn.close();
		}
	}
	
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
