package org.pasedb.pasedbui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ElicitEngine {
	
	public ElicitEngine() {}


	public ResponseItem getLinks(int cntx) throws Exception{
		return getLinks(cntx,-1);
	}


	public ResponseItem getLinks(int cntx, int start) throws Exception{
		
//		System.out.println("********IMCOMING********");
//		System.out.println("cntx: " + cntx);
//		System.out.println("start: " + start);
//		System.out.println("************************");
		Connection conn = null;
		ResponseItem ri = new ResponseItem();
		ri.setPageID(cntx);
		ArrayList<LinkItem> links = new ArrayList<LinkItem>();
		try{
 			conn = DriverManager.getConnection("jdbc:mysql://pasedb:3306/pasedb?user=pasedb&password=alienation"); 
 			LinkItem item = null;
 			
 			PreparedStatement maxrowps=conn.prepareStatement("SELECT max(palinkid) as maxid, min(palinkid) as minid from palink");
 			ResultSet rs = maxrowps.executeQuery();
 			int maxrow = -1;
 			int minrow = -1;
 			if (rs.next()) {
 				maxrow = rs.getInt("maxid");
 				minrow = rs.getInt("minid");
 			}

 			/*
 			StringBuffer fetchSQL = new StringBuffer("SELECT"
 					+" a.palinkid as lastid,"
 				    +" title,"
 				    +" url,"
 				    +" description,"
 				    +" imageurl,"
 				    +" display_height,"
 				    +" display_width,"
 				    +" userid,"
 				    +" DATE_FORMAT(link_date, '%m-%d-%Y') as postedon"
 					+" FROM palink a, tag b"
 					+" where "
 					+" a.palinkid = b.palinkid"
 					+" and b.tag = ?");
 			*/
			StringBuffer fetchSQL = new StringBuffer("SELECT"
 					+" a.palinkid as lastid,"
 				    +" title,"
 				    +" url,"
 				    +" description,"
 				    +" imageurl,"
 				    +" display_height,"
 				    +" display_width,"
 				    +" userid,"
 				    +" DATE_FORMAT(link_date, '%m-%d-%Y') as postedon"
 					+" FROM palink");

 			if (start > 0) {
 				//fetchSQL.append(" and a.palinkid < " + start);
 				fetchSQL.append(" where palinkid < " + start);
 			}
// 			fetchSQL.append(" order by a.palinkid desc limit 20");
			fetchSQL.append(" order by palinkid desc limit 20");

 			//System.out.println(fetchSQL.toString());
 			PreparedStatement fetch=conn.prepareStatement(fetchSQL.toString());
// 			fetch.setInt(1,cntx);
// 			fetch.setInt(2,start);
// 			fetch.setInt(3,ROW_LIMIT);
 			rs = fetch.executeQuery();
 			ri.setNext(-1);
 			ri.setPrev(-1);
 			int lastid = -1;
 			while(rs.next()){
 				item = new LinkItem();
				item.setUrl(rs.getString("url"));
				item.setImgurl(rs.getString("imageUrl"));
				item.setTitle(rs.getString("title"));
				item.setDescription(rs.getString("description"));
//				item.setComment(rs.getString("comment"));
				item.setDisplayHeight(rs.getInt("display_height"));	
				item.setDisplayWidth(rs.getInt("display_width"));
				item.setPostDate(rs.getString("postedon"));
				links.add(item);
				lastid = rs.getInt("lastid");
				ri.setNext(lastid);
 			}
			
 			
 			int PREV_ID = lastid + 40;
 			ri.setPrev(PREV_ID);
				
 			
 			if(lastid == minrow) ri.setNext(-1);
 			if (PREV_ID > (maxrow + 1))ri.setPrev(-1);
 			
 			if (start <= 0) {
// 				System.out.println("*** START >= 0, REMOVING PREVIOUS LINK ***");
 				ri.setPrev(-1);
 			}
 			
 			ri.setLinks(links);
// 			System.out.println("MINROW: " + minrow);
// 			System.out.println("MAXROW: " + maxrow);
// 			System.out.println("NEXT: " + lastid);
// 			System.out.println("PREV: " + PREV_ID);
// 			System.out.println("*____*** RESPONSE_ITEM VALUES ***___*");
// 			System.out.println(ri.toString());
		}catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
		}finally{
			try{conn.close();}catch(Exception e){}
		}
		
 		return ri;
 	}
	
}
