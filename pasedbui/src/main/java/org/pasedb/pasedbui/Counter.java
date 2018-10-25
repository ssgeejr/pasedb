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

public class Counter {
	
	public Counter(HttpServletRequest request) {
//		System.out.println(new java.util.Date().toString());
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("db");
			String remoteIP = request.getRemoteAddr();
			DB db = mongoClient.getDB("pasedb");
			DBCollection countercol = db.getCollection("counter");
			BasicDBObject webhit = new BasicDBObject("ip", remoteIP).append("timestamp", new Date());
			countercol.insert(webhit);	
			
//			DBCollection coll = db.getCollection("links");				
			System.out.println("REMOTE_ADDRESS: " + remoteIP);
//			System.out.println(request.getHeader("X_FORWARDED_FOR"));
//			System.out.println(request.getHeader("HTTP_CLIENT_IP"));
//			System.out.println(request.getHeader("WL-Proxy-Client-IP"));
//			System.out.println(request.getHeader("Proxy-Client-IP"));
//			System.out.println(request.getHeader("REMOTE_ADDR"));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
