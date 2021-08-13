package test;
import java.sql.*;
import java.util.*;;
public class movie {
public static void main(String[] args) {
	try {
  Scanner sc=new Scanner(System.in);
  System.out.println("Enter the movie name");
  String name=sc.nextLine();
  System.out.println("Enter the Actor name ");
  String actor =sc.nextLine();
  System.out.println("Enter the Actross name");
  String actress=sc.nextLine();
  System.out.println("Enter the director name");
  String director=sc.nextLine();
  System.out.println("Enter the date of relese");
  String yor=sc.nextLine();
  Class.forName("oracle.jdbc.driver.OracleDriver");
  Connection con=DriverManager.getConnection
		  ("jdbc:oracle:thin:@localhost:1521:xe","system","madhu8096501604");
  PreparedStatement ps=con.prepareStatement("insert into movie values(?,?,?,?,?)");
  ps.setString(1, name);
  ps.setString(2,actor);
  ps.setString(3, actress);
  ps.setString(4,director);
  ps.setString(5, yor);
  int k=ps.executeUpdate();
  if(k>0)
  {
	  System.out.println("=====inserted sucessfully====");
  }
  sc.close();
  con.close();
	}catch(Exception e) {e.printStackTrace();}
}
}