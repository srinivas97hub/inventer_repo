package com.infyz;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class sendMail {
	public static void send(String to,String subject,String msg){  

		final String user="varneravinder5042@gmail.com";//change accordingly 
		final String pass="ravinder@123";  
		//1st step) Get the session object    
		Properties props = new Properties(); 
		props.put("mail.smtp.user", "varneravinder5042@gmail.com");
		props.put("mail.smtp.password", "ravinder@123");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", 587);
		props.setProperty("java.net.preferIPv4Stack", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.ssl.enable", "false");
	     props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
	    props.put("mail.smtp.auth", "true");
		javax.mail.Session session = null;
	        session = Session.getDefaultInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(user,pass);  
			}  
		});  
		//2nd step)compose message  
		try {  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(user));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			message.setSubject(subject);  
			message.setText(msg);  

			//3rd step)send message  
			Transport.send(message);  

			System.out.println("Done");  

		} catch (Exception e) {  
			throw new RuntimeException(e);  
		}  

	}
}
