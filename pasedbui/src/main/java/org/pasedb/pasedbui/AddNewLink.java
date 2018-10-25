package org.pasedb.pasedbui;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

public class AddNewLink {

	public void addNewLink(HttpServletRequest request) {
		MongoClient mongoClient = null;
		try {
			String remoteIP = request.getRemoteAddr();
			// mongoClient = new MongoClient();
			// mongoClient = new MongoClient("gorkly", 27017);
			// mongoClient = new MongoClient("gorkly");
			mongoClient = new MongoClient("db");
			// or, to connect to a replica set, supply a seed list of members
			// MongoClient mongoClient = new MongoClient(Arrays.asList(new
			// ServerAddress("localhost", 27017),
			// new ServerAddress("localhost", 27018),
			// new ServerAddress("localhost", 27019)));
			
			DB db = mongoClient.getDB("pasedb");
			DBCollection coll = db.getCollection("links");
			List<BasicDBObject> tags = new ArrayList<BasicDBObject>();
			Multiset<String> hashTags = TreeMultiset.create();
			hashTags.add("#fake");
			hashTags.add("#tags");
			// tags.add(new DB)
			BasicDBObject newURL = new BasicDBObject("ip", remoteIP).append("user", "fake-user").append("timestamp",
					new Date());
			newURL.append("title", "fake-title").append("description", "fake-description").append("url", "fake-url")
					.append("tags", hashTags);

			// System.out.println("Data Display");
			coll.insert(newURL);
			DBCursor cursor = coll.find();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				// mongoClient.dropDatabase("test");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
