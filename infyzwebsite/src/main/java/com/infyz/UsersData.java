package com.infyz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UsersData
 */
@WebServlet("/UsersData")
public class UsersData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UsersData() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>total users</title></head>");
		out.println("<body>");
		out.println("<center><h1>All users</h1>");
		Connection con = null;
		Statement stm = null;
		String s=request.getParameter("data");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://99.99.99.16:3306/INFYZ_CMS", "training","2dGSx2lRBPruVmO3");
			stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select * from users");
			pw.println("<table border=0 ");
			pw.println("<tr><td width=137>&nbsp;</td><td>");
			pw.println("<table border=1>");
			pw.println("<tr><th>username</th><th>firstname</th><th>lastname</th><th>mail</th><th>factive</th>");
			pw.println("</tr>");
			while(rs.next())
			{
													  		
				pw.println("<tr><td height=15> "+rs.getString(2)+"</td><td height=15>"+rs.getString(4)+"</td><td height=15 width=70>"+rs.getString(5)+"</td><td width=157 height=15>"+rs.getString(7)+"</td><td height=15>"+rs.getString(6)+"</td>");
				pw.println("</tr>");


			}		
			pw.println("</table>");		
			pw.println("</body></html>");
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
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}

