package com.infyz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}
	int  loginattemppts =2;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String n=request.getParameter("una");  
		String p=request.getParameter("pwd");  
		System.out.println(n+" "+p);
		if(loginattemppts!=0) {
			if(LoginDao.validate(n, p)){  
				out.print("login succefully");
				response.sendRedirect("admin.html");
				/*
				 * RequestDispatcher rd = request.getRequestDispatcher("WebContent");
				 * rd.forward(request, response);
				 */
			} 
			else{  
				loginattemppts--;
				RequestDispatcher rd=request.getRequestDispatcher("login.html");  
				rd.include(request,response);  
				out.println();
				out.println();
				out.println("<span style=color:red> Check the Credentials you Entered</span>");  
				
			}
		}else {
              out.println("<h3 style=color:red>maximum number of attempts are done your account is blocked please contact admin</h3>");
		}

		out.close();  
	}  

}
