package org.pasedb.pasedbui;

import java.util.ArrayList;
import java.util.Date;

public class LinkItem {
	private String url = "";
	private String title = "";
	private String description = "";
	private String imgurl = "";
	private float imgPct = 0.0f;
	private int userID = -1;
	private ArrayList<String> tags = new ArrayList<String>();
	private String GUID = null;
	private String comment = "";
	private Date date = null;
	
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public String getGUID() {
		return GUID;
	}
	public void setGUID(String gUID) {
		GUID = gUID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
