package test;
import java.sql.*;
public class MovieDeatilsShow {
	public static void main(String[] args) 
	{
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","madhu8096501604");
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("select * from movie");
		System.out.println("===Display Records=====");
		while(rs.next())
		{
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
		}
		con.close();
		}catch(Exception e) {e.printStackTrace();}
	}
	}
