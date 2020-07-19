package org.pasedb.pasedbui;

import java.util.ArrayList;

public class ResponseItem {

	private ArrayList<LinkItem> links = new ArrayList<LinkItem>();
	private int next = -99;
	private int prev = -99;
	private int pageid = -1;
	
	@Override
	public String toString() {
		return "PAGEID: " + pageid + "\n"
				+" NEXT_VALUE: " + next + "\n"
				+" PREV_VALUE: " + prev + "\n"
				+" LINK_COUNT: " + links.size();
	}
	
	
//	private String html = "";
//	private String next = "";
//	private String prev = "";
	public int getPageID() {return pageid;}
	public int getNextValue() {return next;}
	public int getPrevValue() {return prev;}
	public void setPageID(int pid) {pageid = pid;}
	public void setNext(int rowid) { next = rowid; }
	public void setPrev(int rowid) { prev = rowid; }
	public void setLinks(ArrayList<LinkItem> lnks) {links = lnks;}
	
	public String getNext() {
		return (next < 0) ? "" : "[<a href=\"/context.jsp?id=" + pageid + "&start=" + next + "\">NEXT &gt&gt</a>]";
	}

	public String getPrev() {
		return (prev < 0) ? "" : "[<a href=\"/context.jsp?id=" + pageid + "&start=" + prev + "\">&lt&lt PREV</a>]";
	}
	
	public String getHtml() {
		return new HtmlEngine().generateTable(links);
	}
	
	public ResponseItem() {}

}
