package org.pasedb.pasedbui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HitCounter {

	public HitCounter() {
		// TODO Auto-generated constructor stub
	}

	
	public String getHitCounts() throws Exception{
		Connection conn = null;
		StringBuffer hits = new StringBuffer();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
 			conn = DriverManager.getConnection("jdbc:mysql://mysql-pasedb.cmiuqauobhwc.us-east-2.rds.amazonaws.com:3306/pasedb?user=pasedb&password=alienation"); 
 			PreparedStatement hc=conn.prepareStatement("select\n" + 
 					" DATE_FORMAT(counter_date, \"%m-%d-%Y\") as a, count(*) as b" + 
 					" from counter\n" + 
 					" group by DATE_FORMAT(counter_date, \"%m-%d-%Y\")" + 
 					" order by DATE_FORMAT(counter_date, \"%m-%d-%Y\") desc");
 			ResultSet rs = hc.executeQuery();
 			int count = 0;
 			while(rs.next()) {
 				hits.append("<tr>\n" + 
 						"<td style=\"text-align: right;width:50%;\">" + rs.getString("a") + "&nbsp;&nbsp;</td>\n" + 
 						"<td>&nbsp;" + rs.getInt("b") + "</td>\n" + 
 						"</tr>\n");
 				count++;
 				if (count == 10) break;
 			}
 		}catch(Exception ex){
			ex.printStackTrace();
//			throw ex;
		}finally{
			try{conn.close();}catch(Exception e){}
		}
		
		return hits.toString();
	}
	
	
}
