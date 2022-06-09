package com.infyz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changepassword")
public class changepassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public changepassword() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter pw = response.getWriter();
	    String uname=request.getParameter("una");
		String nwpw=request.getParameter("npd");
		String cfpw=request.getParameter("cpd");
		if(nwpw.endsWith(cfpw))
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://99.99.99.16:3306/INFYZ_CMS", "training","2dGSx2lRBPruVmO3");
                 PreparedStatement ps=con.prepareStatement("update users set password=? where user_name=?");
                 ps.setString(1, cfpw);
                 ps.setString(2, uname);
                 int k=ps.executeUpdate();
                 if(k>0) {
                	 pw.println("updated sucessfully");
                 }
				
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}else {
			pw.println("password dont match");
		}
	}
}
