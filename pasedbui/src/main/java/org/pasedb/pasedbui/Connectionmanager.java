package org.pasedb.pasedbui;

import java.net.UnknownHostException;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class Connectionmanager {
	private Connection conn = null;

	public Connection ConnectionManager() throws Exception{
		if (conn == null || conn.isClosed())
			conn = DriverManager.getConnection("jdbc:mysql://mysql-pasedb.cmiuqauobhwc.us-east-2.rds.amazonaws.com:3306/pasedb?user=pasedb&password=alienation"); ;
		
		return conn;
	}



}