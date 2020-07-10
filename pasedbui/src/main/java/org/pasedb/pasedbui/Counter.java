package org.pasedb.pasedbui;

import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.jdbc.Driver;

public class Counter {
	private Connection counterConn = null;
	
	public Counter(HttpServletRequest request) {
		System.out.println("init...");
		try {
			String page = "/";
			try{page = request.getRequestURI().trim();}catch(Exception x){}
			String query = "";
			try{query = request.getQueryString().trim();}catch(Exception x){}
			Class.forName("com.mysql.jdbc.Driver");
			counterConn=DriverManager.getConnection("jdbc:mysql://mysql-pasedb.cmiuqauobhwc.us-east-2.rds.amazonaws.com:3306/pasedb?user=pasedb&password=alienation"); 
			PreparedStatement addhit=counterConn.prepareStatement("insert into counter(ip,page,query) values(?,?,?)"); 
			addhit.setString(1, request.getRemoteAddr());
			addhit.setString(2, page);
			addhit.setString(3, query);
			addhit.executeUpdate();
			
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
			try {counterConn.close();}catch(Exception ce) {}
		}
		System.out.println("exit ...");
	}
}
