package org.pasedb.pasedbms;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
//import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class OpenGraphContentManager {
	public OpenGraphItem fetchOGMetaData(String url) throws Exception{
		OpenGraphItem ogi = new OpenGraphItem();
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
	    ogi.setUrl(url);
	    ogi.setImgurl(imageUrl);
	    ogi.setTitle(title);
	    ogi.setDescription(desc);
	    return ogi;
	}
}
