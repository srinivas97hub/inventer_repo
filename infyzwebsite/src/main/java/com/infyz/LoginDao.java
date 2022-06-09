package com.infyz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDao {  
	public static boolean validate(String name,String pass){  
		boolean status=false;  
		Connection con = null;
		Statement stm = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://99.99.99.16:3306/INFYZ_CMS", "training","2dGSx2lRBPruVmO3");
			 
			PreparedStatement ps=con.prepareStatement(  
					"select * from users where user_name=? and password=?");  
			ps.setString(1,name);  
			ps.setString(2,pass);  

			ResultSet rs=ps.executeQuery();  
			status=rs.next();  

		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (con!= null) {
					con.close();
				}
			} catch (SQLException ex) {
			} 
		}
		return status;   
}  
}
