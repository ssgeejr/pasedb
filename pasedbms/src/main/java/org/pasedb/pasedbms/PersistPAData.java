package org.pasedb.pasedbms;

import java.net.UnknownHostException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

public class PersistPAData {
	private MongoConnectionmanager mongo = null;
	MongoDatabase db = null;
	public PersistPAData() {
		try{
			mongo = new MongoConnectionmanager("localhost");
			db = mongo.getDatabase("pasedb");
			fetchFirstRecord();
			testInsert();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			mongo.closeConnection();
		}
	}
	
	private void fetchFirstRecord() throws Exception{
		System.out.println("==========>> Fetch First Record <<==========");
		MongoCollection<Document> collection = db.getCollection("links");
		Document firstcustomer = collection.find().first();
		System.out.println(firstcustomer.toJson());
		System.out.println("__________>> END [Fetch First Record] <<__________");
	}



	public void testInsert() throws Exception{
		String url = "https://www.researchgate.net/post/Parental_alienation_syndrome";
		OpenGraphContentManager ogcm = new OpenGraphContentManager();
		OpenGraphItem ogi = ogcm.fetchOGMetaData(url);
//		db.links.insert({
//			   url: 'http://www.tutorialspoint.com',
//			   title: 'MongoDB Overview', 
//			   description: 'MongoDB is no sql database',
//			   image: 'https://www.somesample.com/',
//			   tags: ['mongodb', 'database', 'NoSQL'],
//			   isize: [1,1],
//			   date: new Date(Date.now()),
//			   user: 2
//			})
		
		ArrayList<String> tags = new ArrayList<String>(); 
		tags.add("mongodb");
		tags.add("database");
		tags.add("NoSQL");
		
		Document doc = new Document("url", ogi.getUrl())
		.append("image", ogi.getImgurl())
		.append("title", ogi.getTitle())
		.append("description", ogi.getDescription())
		.append("tags", tags)
		.append("date", new Date())
		.append("user", 99);
		
		MongoCollection<Document> collection = db.getCollection("links");
		collection.insertOne(doc);
		
	}
	
	public void addMetaData(String url, 
							String comment,
							String title, 
							String desc, 
							String imgurl, 
							String[] tags, 
							float imgpct, 
							int userid) throws Exception{


	}

	public static void main(String[] args) {
		new PersistPAData();

	}

}
