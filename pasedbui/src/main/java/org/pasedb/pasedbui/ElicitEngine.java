package org.pasedb.pasedbui;

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

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ElicitEngine {
	private MongoConnectionmanager connMan = null;
	private  MongoDatabase mongodb = null;
	private String source = "db";
	
	public ElicitEngine() {}
	public ElicitEngine(String dbsource) {
		source = dbsource;
	}
	
	public ArrayList<LinkItem> fetchPALinks() throws Exception{
		ArrayList<LinkItem> links = new ArrayList<LinkItem>();
		try{
			System.out.println("==========>> Fetch Filtered Record <<==========");
			connMan = new MongoConnectionmanager(source);
			mongodb = connMan.getDatabase("pasedb");
			MongoCollection<Document> col = mongodb.getCollection("links");
			FindIterable<Document> fcol = col.find();
			fcol.sort(new BasicDBObject("_id", -1)).limit(5);
			MongoCursor<Document> collection = fcol.iterator();
			while (collection.hasNext()) {
				Document doc = collection.next(); 
				System.out.println("Title:: " + doc.getString("title"));
			}
			System.out.println("__________>> END [Fetch Filtered Record] <<__________");
		}finally{
			connMan.closeConnection();
		}
		return links;
	}

	public ArrayList<LinkItem> getLinks(int cntx) throws Exception{
		ArrayList<LinkItem> links = new ArrayList<LinkItem>();
		try{
			System.out.println("==========>> Fetch Filtered Record <<==========");
			connMan = new MongoConnectionmanager(source);
			mongodb = connMan.getDatabase("pasedb");
			MongoCollection<Document> col = mongodb.getCollection("links");
			FindIterable<Document> fcol = null;
			if(cntx == 0) 
				fcol = col.find();
			else 
				fcol = col.find(Filters.all("tags", cntx));
			fcol.sort(new BasicDBObject("_id", -1)).limit(25);
			MongoCursor<Document> collection = fcol.iterator();
			LinkItem item = null;
			while (collection.hasNext()) {
				Document doc = collection.next(); 
				System.out.println("Title:: " + doc.getString("title"));
				item = new LinkItem();
				item.setUrl(doc.getString("url"));
				item.setImgurl(doc.getString("imageUrl"));
				item.setTitle(doc.getString("title"));
				item.setDescription(doc.getString("desc"));
				item.setComment(doc.getString("comment"));
				item.setDisplayHeight(doc.getInteger("display_height"));	
				item.setDisplayWidth(doc.getInteger("display_width"));
//				{ "_id" : ObjectId("5d01231da7b11b000115c863"), "url" : "https://farzadlaw.com/divorce-and-child-custody/what-is-parental-alienation", "title" : "What is Parental Alienation? | Here is the Surprising Truth for Parents", "desc" : "What is parental alienation? Parents like you want to know. The answer is not only surprising but it will help you avoid being blindsided by making mistakes.", "imageUrl" : "https://dynamix-cdn.s3.amazonaws.com/farzadlawcom/farzadlawcom_563896146.png", "display_height" : 64, "display_width" : 198, "comment" : "99 beep boop beep beep beep boop bip-bip-bip", "userID" : -99, "date" : ISODate("2019-06-12T16:06:53.907Z") }
//				{ "_id" : ObjectId("5d0123b3a7b11b0001641022"), "url" : "https://farzadlaw.com/divorce-and-child-custody/what-is-parental-alienation", "title" : "What is Parental Alienation? | Here is the Surprising Truth for Parents", "desc" : "What is parental alienation? Parents like you want to know. The answer is not only surprising but it will help you avoid being blindsided by making mistakes.", "imageUrl" : "https://dynamix-cdn.s3.amazonaws.com/farzadlawcom/farzadlawcom_563896146.png", "display_height" : 64, "display_width" : 198, "comment" : "99 beep boop beep beep beep boop bip-bip-bip", "userID" : -99, "date" : ISODate("2019-06-12T16:09:23.625Z") }
				links.add(item);
			}
			System.out.println("__________>> END [Fetch Filtered Record] <<__________");
		}finally{
			connMan.closeConnection();
//			closeConnection();
		}
		return links;
	}
		
	public void filterPALinks() throws Exception{
		try{
			connMan = new MongoConnectionmanager();
			mongodb = connMan.getDatabase("pasedb");
			System.out.println("==========>> Fetch Filtered Record <<==========");
			MongoCollection<Document> collection = mongodb.getCollection("links");
//			Document olethaFilter = (Document) collection.find(eq("city", "Olathe")).sort(new BasicDBObject("_id","-1")).limit(3);
//			MongoCursor<Document> cursor = collection.find(eq("city", "Olathe")).sort(new BasicDBObject("_id","-1")).limit(3).iterator();
			MongoCursor<Document> cursor = collection.find().sort(new BasicDBObject("_id","-1")).limit(3).iterator();
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
//			Document olethaFilter = collection.find(eq("city", "Olathe")).first();
//			System.out.println(olethaFilter.toJson());
			System.out.println("__________>> END [Fetch Filtered Record] <<__________");
		}finally{
			connMan.closeConnection();
		}
	}
	
	public static void main(String args[]) throws Exception{
		ElicitEngine ee = new ElicitEngine("localhost");
		ee.fetchPALinks();
	}
	
}
