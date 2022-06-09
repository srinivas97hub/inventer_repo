package com.infyz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MailSent")
public class MailSent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MailSent() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	      
	    String to=request.getParameter("to");  
	    String subject=request.getParameter("subject");  
	    String msg=request.getParameter("msg");  
	          
	    sendMail.send(to, subject, msg);  
	    out.print("message has been sent successfully");  
	    out.close();  
	    }  
	  
		
	}
