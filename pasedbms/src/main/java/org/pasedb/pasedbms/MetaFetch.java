package org.pasedb.pasedbms;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MetaFetch {
	public MetaFetch(){
		try{
/*
		    String url = "http://iloveandneedmydaughter.blogspot.com/2015/12/167-red-flags-of-parental-alienation.html";
		    Response response = Jsoup.connect(url).followRedirects(true).execute();
		    System.out.println(response.url());
		    		    
		    response = Jsoup.connect(url).followRedirects(true).execute();
		    System.out.println(response.header("location"));		    
		    		    
		    String originalUrl = Jsoup.connect(url)
                    .followRedirects(true) //to follow redirects
                    .execute().url().toExternalForm();
		    System.out.println(originalUrl);
		    
		    System.out.println("Status: " + response.statusCode() + " ** URL: " + response.url());
		    System.out.println("Is URL going to redirect : " + response.hasHeader("location"));
			System.out.println("Target : " + response.header("location"));
			
		    if (1 == 1) return;
*/
		    
			String url = "https://www.researchgate.net/post/Parental_alienation_syndrome";
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
//		    	System.out.println("imageUrl: " + imageUrl);
//		    	System.out.println("length: " + imageUrl.trim().length());
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
		    
//		    URL:  max length than 256 characters
		    System.out.println("URL: " + url);
//		    title max length than 100 characters
		    System.out.println("Title: " + title);
//		    description max length than 200 characters
		    System.out.println("Description: " + desc);
//		    Image url max length than 256 characters
		    System.out.println("Image: " + imageUrl);
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MetaFetch();
	}

}
