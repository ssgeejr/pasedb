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

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ElicitEngine {
	private MongoConnectionmanager connMan = null;
	private  MongoDatabase mongodb = null;
	private String source = "db";
	public ElicitEngine() {

	}
	
	public ElicitEngine(String dbsource) {
		source = dbsource;
	}
	
	private ArrayList<LinkItem> fetchPALinks() throws Exception{
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
//			closeConnection();
		}
		return links;
	}
		
	private void filterPALinks() throws Exception{
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
