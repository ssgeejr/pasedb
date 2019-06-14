package org.pasedb.pasedbms;

public class OpenGraphItem {
	private String url = "";
	private String title = "";
	private String description = "";
	private String imgurl = "";
	private float imgPct = 0.0f;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = setlen(url,256);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = setlen(title,100);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = setlen(description,200);
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = setlen(imgurl,256);
	}
	public float getImgPct() {
		return imgPct;
	}
	public void setImgPct(float imgPct) {
		this.imgPct = imgPct;
	}
	private String setlen(String in, int len){
		if (in == null) in = "";
		else if (in.length() > len) in = in.substring(0,len);
		return in;
	}
	
}
