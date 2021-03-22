package org.pasedb.pasedbui;

import java.sql.*;

public class Connectionmanager {
	private Connection conn = null;

	public Connection ConnectionManager() throws Exception{
		if (conn == null || conn.isClosed())
			conn = DriverManager.getConnection("jdbc:mysql://pasedb:3306/pasedb?user=pasedb&password=alienation"); ;
		
		return conn;
	}



}
