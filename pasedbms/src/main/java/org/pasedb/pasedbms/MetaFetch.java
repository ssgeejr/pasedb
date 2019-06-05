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
		    
			String url = "http://iloveandneedmydaughter.blogspot.com/2015/12/167-red-flags-of-parental-alienation.html";
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
		    	
				URL iurl=new URL(imageUrl);
				BufferedImage image = ImageIO.read(iurl);
				int height = image.getHeight();
				int width = image.getWidth();
				System.out.println("Height : "+ height);
				System.out.println("Width : "+ width);
				// add the code to make the image smaller here -- revision 2, no need to work on this now!
		    }
		    
		    System.out.println("URL: " + url);
		    System.out.println("Title: " + title);
		    System.out.println("Description: " + desc);
		    System.out.println("Image: " + imageUrl);
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new MetaFetch();
	}

}
