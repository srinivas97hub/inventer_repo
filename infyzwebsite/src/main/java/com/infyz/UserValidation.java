package com.infyz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserValidation {
	public boolean checkUsername(String username){
		System.out.println("hi validator");
		ArrayList<String> ar=new ArrayList<String>();
		Connection con=null;
		Statement st = null;
		ResultSet rs;
		boolean check = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://99.99.99.16:3306/INFYZ_CMS", "training","2dGSx2lRBPruVmO3");
			st=con.createStatement();
			rs = st.executeQuery( "SELECT USER_NAME FROM users");
			while(rs.next())
			{
				ar.add(rs.getString(1));
			}
	      for(String s:ar)
	      {
	    	  if(s.equals(username))
	    	  {
	    		  check=false;
	    		  break;
	    	  }
	      }
		System.out.println(check);
		}catch(Exception e) {
			
		}
		return check;
	}
}

