package com.north.pasedb.etl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MongoMySQLETL {
	
	private MongoClient mongoClient = null;
	private MongoDatabase mongodb = null;
	private Connection conn = null;
	
	
	private final String source = "pasedb.org";
	private PreparedStatement pslink = null;
	private PreparedStatement pstag = null;
	
//	private final DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm");
	private final DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	
	
	
	public MongoMySQLETL() {
		try {
			openConnections();
			executETL();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			try{conn.close();}catch(Exception e){}
			try{mongoClient.close();}catch(Exception e){}
		}
	}
	
	public void executETL() throws Exception{
		System.out.println("__________>> BEGIN ETL PROCESS <<__________");
		MongoCollection<Document> col = mongodb.getCollection("links");
		FindIterable<Document> fcol = null;
		fcol = col.find();
//		fcol.sort(new BasicDBObject("_id", 1)).limit(25);
		fcol.sort(new BasicDBObject("_id", 1));
		MongoCursor<Document> collection = fcol.iterator();
		Document doc = null;
		int reccount = 0;
		while (collection.hasNext()) {
			doc = collection.next(); 
			System.out.println("title:: " + doc.getString("title"));
			System.out.println("url:: " + doc.getString("url"));
			System.out.println("imageUrl:: " + doc.getString("imageUrl"));
			System.out.println("desc:: " + doc.getString("desc"));
			System.out.println("comment:: " + doc.getString("comment"));
			System.out.println("tags::");
			List<Integer> tags =  null;
			try {
				tags = (List<Integer>) doc.get("tags");	
				if (tags == null) {
					System.out.println("     tag:: tag value is null - defauling to 0");
				}else {
					for (Integer tag : tags) {
						System.out.println("     tag:: " + tag.intValue());
					}
				}
			}catch(NullPointerException npe) {
				System.out.println(" ... missing tags value");
			}catch(java.lang.ClassCastException cce) {
				System.out.println(" ... the old version of the software used arraylist inside of an array list");
				tags =  null;
			}
			System.out.println("display_height:: " + doc.getInteger("display_height"));	
			System.out.println("display_width:: " + doc.getInteger("display_width"));
			System.out.println("date:: " + df.format(doc.getDate("date")));
			
			
			mysqlInjector(doc.getString("title"),
							doc.getString("url"), 
							doc.getString("imageUrl"),
							doc.getString("desc"),
							doc.getInteger("display_height"),
							doc.getInteger("display_width"),
							df.format(doc.getDate("date")),
							tags);
			
			
			
			reccount++;
			
		}
		System.out.println("RECORE INSERTED ["+ reccount + "]");
		System.out.println("__________>> END ETL PROCESS <<__________");
	}	
	
	
	
	
	private void mysqlInjector(String title,
								String url, 
								String imageurl,
								String desc,
								int height,
								int width,
								String date,
								List<Integer> tags)
								throws Exception{
		int palinkid = -99;
		try{
			
			
//			title,url,description,imageurl,display_height,display_width,userid
			
			pslink.setString(1, title);
			pslink.setString(2, url);
			pslink.setString(3, desc);
			pslink.setString(4, imageurl);
			pslink.setInt(5, height);
			pslink.setInt(6, width);
			pslink.setString(7, date);
			
 			pslink.executeUpdate();
			ResultSet ks = pslink.getGeneratedKeys();  
			
//			tag,palinkid
			
			palinkid = ks.next() ? ks.getInt(1) : -1;
			if (tags == null) {
				pstag.setInt(1, 0);
				pstag.setInt(2, palinkid);
				pstag.executeUpdate();
			}else {	
				for (Integer tag : tags) {
	//				System.out.println("     tag:: " + tag.intValue());
					pstag.setInt(1, tag.intValue());
					pstag.setInt(2, palinkid);
					pstag.executeUpdate();
				}
			}

		}catch(Exception ex){
			throw ex;
		}

	}
	
	private void openConnections() throws Exception{
		try{
			System.out.println("==========>> OPENING MYSQL CONNECTION <<==========");
 			conn = DriverManager.getConnection("jdbc:mysql://mysql-pasedb.cmiuqauobhwc.us-east-2.rds.amazonaws.com:3306/pasedb?user=pasedb&password=alienation"); 
// 			System.out.println("connected: " + !conn.isClosed());
 			System.out.println("==========>> MYSQL CONNECTION ESTABLISHED <<==========");
 			String linksql = "INSERT INTO palink(title,url,description,imageurl,display_height,display_width,link_date,userid) VALUES(?,?,?,?,?,?,?,-99)";
			String tagsql = "INSERT INTO tag(tag,palinkid) VALUES(?,?)";
			pslink = conn.prepareStatement(linksql,Statement.RETURN_GENERATED_KEYS);
 			pstag = conn.prepareStatement(tagsql);
 			System.out.println("==========>> MYSQL STATEMENTS PREPARED <<==========");
 						
 			System.out.println("==========>> OPENING MONGO CONNECTION <<==========");
 			
			mongoClient = new MongoClient(source);
			mongodb = mongoClient.getDatabase("pasedb");

			System.out.println("==========>> MONGO CONNECTION ESTABLISHED <<==========");
 			
		}catch(Exception ex){
			throw ex;
		}
	}
	
	
	public static void main(String[] args) {
		new MongoMySQLETL();

	}

}
