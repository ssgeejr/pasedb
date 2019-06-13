package org.pasedb.pasedbui;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB; 
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

public class Counter {
	private MongoConnectionmanager connMan = null;
	private  MongoDatabase mongodb = null;
	
	public Counter(HttpServletRequest request) {
		try {
			connMan = new MongoConnectionmanager("db");
			mongodb = connMan.getDatabase("pasedb");
			MongoCollection<Document> collection =  mongodb.getCollection("counter");	
			String qeuery = "";
			try{qeuery = request.getQueryString().trim()}catch(Exception x){}
			collection.insertOne(new Document("ip", request.getRemoteAddr()).append("page",request.getRequestURI()).append("query",qeuery).append("timestamp", new Date()));	
//			DBCollection coll = db.getCollection("links");				
//			System.out.println("REMOTE_ADDRESS: " + remoteIP);
//			System.out.println(request.getHeader("X_FORWARDED_FOR"));
//			System.out.println(request.getHeader("HTTP_CLIENT_IP"));
//			System.out.println(request.getHeader("WL-Proxy-Client-IP"));
//			System.out.println(request.getHeader("Proxy-Client-IP"));
//			System.out.println(request.getHeader("REMOTE_ADDR"));
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			connMan.closeConnection();
		}
	}
}
