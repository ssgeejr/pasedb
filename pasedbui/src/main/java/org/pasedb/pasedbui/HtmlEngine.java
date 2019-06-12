package org.pasedb.pasedbui;

import java.util.ArrayList;

public class HtmlEngine {

	public HtmlEngine() {
		// TODO Auto-generated constructor stub
	}
	
	
	private StringBuffer sb = new StringBuffer();
	
	public String generateTable(ArrayList<LinkItem> links){
		StringBuffer lisb = new StringBuffer();
		for(LinkItem li: links){
			lisb.append(generateTable(li));
		}
		return lisb.toString();
	}
	
	public String generateTable(LinkItem li){
		sb.setLength(0);
		sb.append("<tbody><tr><td>");
		sb.append("<img src=\"" + li.getImgurl() + "\" height=\"" + li.getDisplayHeight() + "\"  width=\"" + li.getDisplayWidth() + "\"><br>");
		sb.append("</td></tr><tr><td>");
//		sb.append("<b>Title: </b>" + li.getTitle());
		sb.append("<b>" + li.getTitle());
		sb.append("</b></td></tr><tr><td>");
//		sb.append("<b>Description: </b>" + li.getDescription());
		sb.append("<b>" + li.getDescription());
		sb.append("</b></td></tr><tr><td>");
		sb.append("<b>{username}: </b>" + li.getComment());
		sb.append("</td></tr></tbody>");
		return sb.toString();
	}

}
