package com.infyz;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserRegister() {
        super();
     
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
		Date d=new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
        String m=ft.format(d);
        String username = request.getParameter("name");
        UserValidation user=new UserValidation();
       if(user.checkUsername(username)==true) {
        String  password = request.getParameter("password");
        System.out.println(password);
        String fristname = request.getParameter("fristname");
        System.out.println(fristname);
        String lastname = request.getParameter("lastname");
        System.out.println(lastname);
        String email = request.getParameter("email");
        System.out.println(email);
        Connection con = null;
		//Statement stm = null;
		 PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://99.99.99.16:3306/INFYZ_CMS", "training","2dGSx2lRBPruVmO3");
                ps = con.prepareStatement
                    ("insert into users(user_name,password,first_name,last_name,f_active,email_id,created_by,craeted_date,Modified_by,modified_date) values(?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fristname);
            ps.setString(4, lastname);
            ps.setInt(5, 1);
            ps.setString(6, email);
            ps.setInt(7 , 1);
            ps.setString(8,m);
            ps.setInt(9, 1);
            ps.setString(10, m);
            ps.executeUpdate();
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con!= null) {
					con.close();
				}
			} catch (SQLException ex) {
			}
		}
       }else {
    	   out.println("username already wxist");
       }
		}
	}


