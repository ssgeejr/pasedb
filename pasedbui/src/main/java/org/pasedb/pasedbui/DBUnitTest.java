package org.pasedb.pasedbui;

import java.sql.*;

public class DBUnitTest {

	Connection connection = null;

	public DBUnitTest() {

		try {

			connection = DriverManager.getConnection(
					"jdbc:mysql://pasedb:3306/pasedb?user=pasedb&password=alienation");
			PreparedStatement newlink = connection.prepareStatement(
					"insert into palink(title,url,description,imageurl,display_height,display_width,userid) values(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			int palinkid = -1;
			newlink.setString(1, "title");
			newlink.setString(2, "url");
			newlink.setString(3, "");
			newlink.setString(4, "");
			newlink.setInt(5, 0);
			newlink.setInt(6, 0);
			newlink.setInt(7, 0);
			newlink.executeUpdate();
			ResultSet rs = newlink.getGeneratedKeys();
			palinkid = rs.next() ? rs.getInt(1) : -1;

			System.out.println("PALINKID: " + palinkid);
			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public DBUnitTest(int x) {

		try {

			String page = "/";
			String query = "4";

			connection = DriverManager.getConnection(
					"jdbc:mysql://pasedb:3306/pasedb?user=pasedb&password=alienation");
			PreparedStatement addhit = connection.prepareStatement("insert into counter(ip,page,query) values(?,?,?)");
			addhit.setString(1, "127.0.0.1");
			addhit.setString(2, page);
			addhit.setString(3, query);
			addhit.executeUpdate();

			connection.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DBUnitTest();

	}

}
