package org.pasedb.pasedbui;

import java.sql.*;


public class GravyTrainTesting {

	public GravyTrainTesting() {
		Connection conn = null;
		int rownum = 0;
		try{
 			conn = DriverManager.getConnection("jdbc:mysql://mysql-pasedb.cmiuqauobhwc.us-east-2.rds.amazonaws.com:3306/pasedb?user=pasedb&password=alienation"); 
 			String sql = "INSERT INTO palink(title,url,description,imageurl,display_height,display_width,userid) " + 
 					"VALUES('Parental Alienation','https://emmm.org.au/parental-alienation','is Emotional and Psychological Abuse','http://img1.wsimg.com/isteam/stock/3683',130,194,-99)";
			String tagsql = "INSERT INTO tag(tag,palinkid)\n" + 
					" VALUES(0,?)";
 			System.out.println(sql);
 			PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
 			PreparedStatement tags = conn.prepareStatement(tagsql);
 			int palinkid = -1;
 			for (rownum = 0; rownum < 100; rownum ++) {
 				pst.executeUpdate();
				ResultSet ks = pst.getGeneratedKeys();  
				palinkid = ks.next() ? ks.getInt(1) : -1;
				tags.setInt(1, palinkid);
				tags.executeUpdate();
 			}
 			System.out.println("Rows inserted: " + rownum);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{conn.close();}catch(Exception e){}
		}
	}

	public static void main(String[] args) {
		new GravyTrainTesting();

	}

}
