package org.pasedb.pasedbui;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.*;

public class DBUnitTest {

	public DBUnitTest() {
		
		try {
			
			String page = "/";
			String query = "4";

			Connection counterConn=DriverManager.getConnection("jdbc:mysql://mysql-pasedb.cmiuqauobhwc.us-east-2.rds.amazonaws.com:3306/pasedb?user=pasedb&password=alienation"); 
			PreparedStatement addhit=counterConn.prepareStatement("insert into counter(ip,page,query) values(?,?,?)"); 
			addhit.setString(1, "127.0.0.1");
			addhit.setString(2, page);
			addhit.setString(3, query);
			addhit.executeUpdate();
			
			counterConn.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DBUnitTest();

	}

}
