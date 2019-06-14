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


public class TestRunner {
	private MongoConnectionmanager connMan = null;
	private  MongoDatabase mongodb = null;
	private String source = "localhost";

	public TestRunner() {
		
		
		
		
		try{
//			AddNewLink newlink = new AddNewLink();
//			newlink.fetchOGMetaData("https://farzadlaw.com/divorce-and-child-custody/what-is-parental-alienation", "comment", 99);
			
			
			
//			alphaDogTest();
			omegaDogTest();
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void omegaDogTest() throws Exception{
		
		try{
			System.out.println("==========>> Fetch Filtered Record <<==========");
//			connMan = new MongoConnectionmanager(source);
//			mongodb = connMan.getDatabase("pasedb");
			int cntx = 0;
			System.out.println(new HtmlEngine().generateTable(new ElicitEngine("localhost").getLinks(cntx)));
			System.out.println("__________>> END [Fetch Filtered Record] <<__________");
		}finally{
//			connMan.closeConnection();
//			closeConnection();
		}
	}
	
	private void alphaDogTest() throws Exception{
		
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
				System.out.println(doc.getString("title"));
			}
			System.out.println("__________>> END [Fetch Filtered Record] <<__________");
		}finally{
			connMan.closeConnection();
//			closeConnection();
		}

		
	}

	public static void main(String[] args) {
		new TestRunner();

	}

}
