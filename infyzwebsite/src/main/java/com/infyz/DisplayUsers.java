package com.infyz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayUsers {
public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://99.99.99.16:3306/INFYZ_CMS", "training","2dGSx2lRBPruVmO3");

			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from users");
			while(rs.next())
			{
				System.out.println(rs.getString(2)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(7)+"\t"+rs.getString(6));
			}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
